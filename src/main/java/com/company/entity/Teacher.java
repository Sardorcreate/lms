package com.company.entity;

import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private String subjectId;
    @Column
    private String academicDegree;
    @Column
    private String position;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column
    private Integer kafedraId;
}
