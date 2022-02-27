package com.orphan.api.user.dto;

import com.orphan.common.annotation.Identification;
import com.orphan.common.annotation.Phone;
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

    private String email;

    private String fullName;

    private String phone;

    private String identification;

    private String image;

    private Set<RoleDto> roles;
}
