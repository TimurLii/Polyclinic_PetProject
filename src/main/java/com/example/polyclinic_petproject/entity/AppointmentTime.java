package com.example.polyclinic_petproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "appointmentTime", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @Override
    public String toString() {
        return "AppointmentTime{" +
                "id=" + id +
                "patient= " + patient.getFullName() +
                "doctor=" + doctor.getFullName() +
                '}';
    }
}

