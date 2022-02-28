package com.orphan.common.service;

import com.orphan.api.user.dto.UserDto;
import com.orphan.api.user.mapper.UserMapper;
import com.orphan.common.entity.Role;
import com.orphan.common.entity.User;
import com.orphan.common.repository.RoleRepository;
import com.orphan.common.repository.UserRepository;
import com.orphan.common.request.PasswordRequest;
import com.orphan.common.request.SignUpRequest;
import com.orphan.common.response.MessageResponse;
import com.orphan.enums.ERole;
import com.orphan.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public MessageResponse save(SignUpRequest signUpRequest) {

        if (existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!", false);
        }
        if (existsByIdentification(signUpRequest.getIdentification())) {
            return new MessageResponse("Error: Identification is already in use!", false);
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getFullName(), signUpRequest.getPhone(), signUpRequest.getIdentification(), signUpRequest.getImage());


        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles.size() != 0) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "manager":
                        Role modRole = findByName(ERole.ROLE_MANAGER);
                        roles.add(modRole);
                        break;
                }
            });
        } else {
            Role userRole = findByName(ERole.ROLE_MANAGER);
            roles.add(userRole);
        }

        user.setRoles(roles);
        save(user);

        return new MessageResponse("User registered successfully!", true);
    }

    public MessageResponse updateUser(UserDto userDto,Integer id) {
        User user = userRepository.findById(id).get();
        if(!user.getEmail().equals(userDto.getEmail())){
            if (userRepository.existsByEmail(userDto.getEmail())) {
            return new MessageResponse("Error: Email is already in use!", false);
        }}
        if(!user.getIdentification().equals(userDto.getIdentification())){
          if (userRepository.existsByIdentification(userDto.getIdentification())) {
            return new MessageResponse("Error: Identification is already in use!", false);
        }}

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setIdentification(userDto.getIdentification());
        user.setPhone(userDto.getPhone());
        if (userDto.getImage() != null && !userDto.getImage().isEmpty()) {
            user.setImage(userDto.getImage());
        }
        save(user);
        return new MessageResponse("Success: User update successfully!!", true);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException("Không tìm thấy bất kỳ tài khoản nào có Id = " + id);
        }
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByIdentification(String identification) {
        return userRepository.existsByIdentification(identification);
    }

    public Role findByName(ERole roleName) {
        return roleRepository.findByName(roleName);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void saveAllRole(Set<Role> list) {
        roleRepository.saveAll(list);
    }

    public List<UserDto> listAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        userList.stream().forEach(user -> userDtoList.add(UserMapper.INSTANCE.userToUserDto(user)));
        return userDtoList;
    }

    public UserDto findById(Integer id) {
        User user = userRepository.findById(id).get();
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void changePassword(PasswordRequest password, Integer id) {
        User user = userRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        save(user);
    }

}
