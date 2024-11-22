package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsByStatus(Booking.BookingStatus status);

    boolean existsByLocalDateAndTimeEnum(LocalDate date, AppointmentTimeEnum appointmentTimeEnum);

    boolean existsByLocalDateAndAppointmentTimeAndTimeEnum(LocalDate date, AppointmentTime appointmentTime, AppointmentTimeEnum appointmentTimeEnum);


}
