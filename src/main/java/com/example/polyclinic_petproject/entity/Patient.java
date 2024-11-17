package com.example.polyclinic_petproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "patients")
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

//        private Long DoctorName;
        private String fullName;
        private int age;
        private String gender;
        private String contactDetails;
        private String login;
        private String password;

//        @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
//        private Set<Schedule> appointments = new HashSet<>();
    }

