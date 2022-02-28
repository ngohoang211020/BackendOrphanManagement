package com.orphan.api.user.dto;

import com.orphan.common.annotation.Identification;
import com.orphan.common.annotation.Phone;
import com.orphan.common.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String fullName;

    @Phone
    private String phone;

    @Identification
    private String identification;

    private String image;

    private Set<Role> roles;
}
