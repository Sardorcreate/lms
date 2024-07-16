package com.company.entity;

import com.company.enums.Role;
import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "userr")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String phone;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id")
    private Passport passport;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column
    private String imagePath;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
    @Column
    private Boolean visible;
}
