package com.orphan.common.request;

import com.orphan.common.annotation.Password;
import lombok.Getter;

@Getter
public class PasswordRequest {

    @Password
    private String password;
}
