package com.company.entity;

import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "task_by_teacher")
@Getter
@Setter
public class TaskByTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer groupId;
    @Column
    private Integer subjectId;
    @Column
    private Integer teacherId;
    @Column
    private Integer maxMark;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column
    private LocalDate deadline;
    @Column
    private String filePath;
}
