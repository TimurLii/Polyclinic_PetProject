package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Long> {
    List<Booking> findByAppointmentTime(AppointmentTime appointmentTime);
}
