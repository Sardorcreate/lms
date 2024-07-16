package com.company.dto;

import com.company.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTDto {
    private Integer id;
    private String login;
    private Role role;
}
