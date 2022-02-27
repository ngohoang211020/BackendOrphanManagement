package com.orphan.controller.auth;

import com.orphan.common.entity.Role;
import com.orphan.common.entity.User;
import com.orphan.common.request.LoginRequest;
import com.orphan.common.request.SignUpRequest;
import com.orphan.common.response.JwtResponse;
import com.orphan.common.response.MessageResponse;
import com.orphan.common.service.UserService;
import com.orphan.config.security.jwt.JwtUtils;
import com.orphan.config.security.service.UserDetailsImpl;
import com.orphan.enums.ERole;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "Đăng nhập", description = "Trả về jwt và thông tin user", tags = {"Login/logout"})
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            //Set chuỗi authentication đó cho UserPrincipal
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Trả về jwt cho người dùng.
            String jwt = jwtUtils.generateJwtToken(authentication);// Tạo ra jwt từ chuỗi authentication

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();//lay thong tin user
            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toSet());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId()
                    , userDetails.getUsername(), userDetails.getFullName()
                    , userDetails.getPhone(), userDetails.getIdentification()
                    , userDetails.getImage(), userDetails.password(), roles));

        } catch (AuthenticationException e) {
            return ResponseEntity.ok(new MessageResponse("Error: Authentication Fail", false));

        }

    }


    @Operation(summary = "Đăng ký", description = "Trả về message", tags = {"Login/logout"})
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest signUpRequest) {

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!", false));
        }
        if (userService.existsByIdentification(signUpRequest.getIdentification())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Identification is already in use!", false));
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFullName(), signUpRequest.getPhone(), signUpRequest.getIdentification(), signUpRequest.getImage());


        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles.size() != 0) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = userService.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "manager":
                        Role modRole = userService.findByName(ERole.ROLE_MANAGER);
                        roles.add(modRole);
                        break;
                }
            });
        } else {
            Role userRole = userService.findByName(ERole.ROLE_MANAGER);
            roles.add(userRole);
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!", true));
    }
}
