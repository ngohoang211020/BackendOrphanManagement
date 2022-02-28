package com.orphan.common.request;

import com.orphan.common.annotation.Identification;
import com.orphan.common.annotation.Password;
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
public class SignUpRequest {
    @Email(message = "Email không hợp lệ")
    private String email;

    @Password
    private String password;

    private String fullName;

    @Phone
    private String phone;

    @Identification
    private String identification;
    private String image;
    private Set<String> roles;
}
