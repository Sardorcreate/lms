package com.company.dto;

import com.company.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
    private Integer id;
    private Language language;
    private String name;
    private Integer kafedraId;
}
