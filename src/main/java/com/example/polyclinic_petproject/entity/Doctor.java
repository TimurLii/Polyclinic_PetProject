package com.example.polyclinic_petproject.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "doctors")

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String specialty;
}
