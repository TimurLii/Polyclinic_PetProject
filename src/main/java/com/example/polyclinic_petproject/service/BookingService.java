package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingByStatus(Booking.BookingStatus bookingStatus) {
        List<Booking> allBookingBusy = bookingRepository.findBookingsByStatus(bookingStatus);
        return allBookingBusy;
    }

    public List<Booking> getBookingsByStatusBusy(Booking.BookingStatus bookingStatus) {
        return bookingRepository.findBookingsByStatus(bookingStatus);
    }

    public boolean existsByDateAndTime(LocalDate localDate, AppointmentTimeEnum appointmentTimeEnum) {
        return bookingRepository.existsByLocalDateAndTimeEnum(localDate, appointmentTimeEnum);
    }

    public boolean existsByLocalDateAndAppointmentTimeAndTimeEnum(LocalDate localDate, AppointmentTime appointmentTime, AppointmentTimeEnum appointmentTimeEnum) {
        return bookingRepository.existsByLocalDateAndAppointmentTimeAndTimeEnum(localDate,appointmentTime,appointmentTimeEnum);
    }

}
