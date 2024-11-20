package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.repository.AppointmentTimeRepository;
import com.example.polyclinic_petproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTimeService {

    @Autowired
    private AppointmentTimeRepository appointmentTimeRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<AppointmentTime> getAvailableAppointmentTimes() {
        List<AppointmentTime> allAppointmentTimes = appointmentTimeRepository.findAll();

        // Получаем все забронированные времена
        List<Booking> bookedTimes = bookingRepository.findAll();

        // Фильтруем доступные времена
        return allAppointmentTimes.stream()
                .filter(appointmentTime -> bookedTimes.stream()
                        .noneMatch(booking -> booking.getAppointmentTime().equals(appointmentTime)))
                .toList();
    }
}
