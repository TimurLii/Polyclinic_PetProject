package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
