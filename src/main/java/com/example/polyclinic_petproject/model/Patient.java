package com.example.polyclinic_petproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long DoctorName;
        private String fullName;
        private int age;
        private String gender;
        private String contactDetails;
        private String medicalHistory;
        @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Schedule> appointments = new HashSet<>();
    }

