package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private int age;
    private String gender;
    private String contactDetails;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Role> roles = Collections.singletonList(Role.USER);

    public boolean isEnabled() {
        return true;
    }
}