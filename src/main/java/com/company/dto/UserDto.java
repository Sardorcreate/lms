package com.company.dto;

import com.company.enums.Role;
import com.company.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private PassportDto passportDto;
    private Integer passportId;
    private Status status;
    private String imagePath;
    private Role role;
    private Boolean visible;
    private String jwt;
}
