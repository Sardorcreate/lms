package com.company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "lesson")
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer subjectId;
    @Column
    private Integer room;
    @Column
    private Integer teacherId;
    @Column
    private Integer groupId;
    @Column
    private Integer couple;
    @Column
    private LocalDate date;
}
