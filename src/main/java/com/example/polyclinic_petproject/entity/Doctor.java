package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;


@Entity
@Data
@Table(name = "doctors")

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String specialty;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Role> roles = Collections.singletonList(Role.ADMIN);
}
