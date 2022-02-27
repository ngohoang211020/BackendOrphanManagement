package com.orphan.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private Integer id;
    private String email;
    private String fullName;
    private String phone;
    private String identification;
    private String image;
    private String password;
    private Set<String> roles;

}
