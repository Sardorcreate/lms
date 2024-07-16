package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilterDto {

    private String name;
    private String surname;
    private String phone;
    private PassportDto passportDto;

}
