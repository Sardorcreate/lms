package com.company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "task_by_student")
@Getter
@Setter
public class TaskByStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer studentId;
    @Column
    private Integer taskByTeacherId;
    @Column
    private String filePath;
    @Column
    private LocalDate date;
}
