package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private AppointmentTimeEnum timeEnum;

//    @ManyToOne
//    @JoinColumn(name = "appointment_time_id")
//    private AppointmentTime appointmentTime;
}