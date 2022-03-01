package com.orphan.controller.admin;

import com.orphan.api.user.dto.UserDto;
import com.orphan.common.constants.SuccessConstants;
import com.orphan.common.request.PasswordRequest;
import com.orphan.common.request.SignUpRequest;
import com.orphan.common.response.MessageResponse;
import com.orphan.common.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Lấy danh sách tất cả tài khoản User", description = "Trả về danh sách user", tags = {"Admin"})
    @GetMapping
    public ResponseEntity<?> viewAllUser() {
        List<UserDto> userDtoList = userService.listAllUser();
        return ResponseEntity.ok(userDtoList);
    }

    @Operation(summary = "Xem chi tiết thông tin tài khoản user", description = "Trả về thông tin chi tiết của 1 User tuỳ chọn theo id", tags = {"Admin"})
    @GetMapping("/{id}")
    public ResponseEntity<?> viewDetailUser(@PathVariable("id") Integer id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Thêm tài khoản user", description = "Thêm 1 tài khoản User ", tags = {"Admin"})
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok(userService.save(signUpRequest));
    }

    @Operation(summary = "Cập nhật tài khoản user", description = "Cập nhật thông tin 1 tài khoản User theo tuỳ chọn theo id ", tags = {"Admin"})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.updateUser(userDto, id));
    }

    @Operation(summary = "Thay đổi mật khẩu tài khoản User", description = "Thay đổi mật khẩu của 1 tài khoản User tuỳ chọn theo id ", tags = {"Admin"})
    @PostMapping("/changePassword/{id}")
    public ResponseEntity<?> changePasswordUser(@PathVariable("id") Integer id, @RequestBody PasswordRequest password) {
        userService.changePassword(password, id);
        return ResponseEntity.ok(new MessageResponse(SuccessConstants.CHANGE_PASSWORD_USER_SUCCESS, true));
    }

    @Operation(summary = "Xoá tài khoản user", description = "Xoá 1 tài khoản User tuỳ chọn theo id", tags = {"Admin"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(SuccessConstants.DELETE_USER_SUCCESS, true));
    }


}
