package com.orphan.common.request;

import lombok.*;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
