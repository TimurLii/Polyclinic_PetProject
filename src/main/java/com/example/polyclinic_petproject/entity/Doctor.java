package com.example.polyclinic_petproject.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String specialty;

//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Schedule> appointments = new HashSet<>();
}
