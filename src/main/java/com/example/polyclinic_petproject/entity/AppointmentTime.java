package com.example.polyclinic_petproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

//    @OneToMany(mappedBy = "appointmentTime")
//    private List<Booking> bookings;
}
