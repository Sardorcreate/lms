package com.company.entity;

import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mark")
@Getter
@Setter
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer score;
    @Column
    private Integer taskByTeacherId;
    @Column
    private Integer studentId;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
}
