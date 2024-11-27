package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_roles", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles = new ArrayList<>(Collections.singletonList(Role.ADMIN));
}
