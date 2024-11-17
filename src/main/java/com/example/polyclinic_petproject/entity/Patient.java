package com.example.polyclinic_petproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
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
//        @ElementCollection(targetClass = Role.class,fetch = FetchType.LAZY )
//        @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name= "user_id"))
//        @Enumerated(EnumType.STRING)
//        private Set<Role> roles;
//        @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
//        private Set<Schedule> appointments = new HashSet<>();
    }

