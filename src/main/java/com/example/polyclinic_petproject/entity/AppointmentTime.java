package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AppointmentTimeEnum timeEnum;

    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;


}
