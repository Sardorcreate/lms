package com.company.entity;

import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String question;
    @Column
    private String firstOption;
    @Column
    private String secondOption;
    @Column
    private String thirdOption;
    @Column
    private String fourthOption;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
}
