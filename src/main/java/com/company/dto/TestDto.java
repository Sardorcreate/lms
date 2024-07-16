package com.company.dto;

import com.company.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto {
    private Integer id;
    private String question;
    private String firstOption;
    private String secondOption;
    private String thirdOption;
    private String fourthOption;
    private Status status;
}
