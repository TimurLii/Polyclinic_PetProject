package com.example.polyclinic_petproject.model;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import jakarta.persistence.*;

@Entity
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AppointmentTimeEnum timeEnum;

    @ManyToOne
    private Patient patient;
}
