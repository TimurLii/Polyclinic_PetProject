package com.example.polyclinic_petproject.entity;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private AppointmentTimeEnum timeEnum;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_time_id")
    @JsonBackReference
    private AppointmentTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Override
    public String toString() {
        return "Booking{" +
                "id= " + id +
                "localeDateTime=" + localDate +
                "appointmentTimeEnum= " + timeEnum.getDisplayName() +
                ", appointmentTimeId= " + (appointmentTime != null ? appointmentTime.getId() : "null ") +
                "bookingStatus= " + status +
                '}';
    }

    public enum BookingStatus {
        BUSY,
        FREE,
        CANCELED;
    }
}
