package com.company.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ebsance")
@Getter
@Setter
public class Ebsance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer lessonId;
    @Column
    private Integer studentId;
    @Column
    private Integer subjectId;
}
