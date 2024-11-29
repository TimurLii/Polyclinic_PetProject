package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import com.example.polyclinic_petproject.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentTime saveAppointment(AppointmentTime appointmentTime) {
        return appointmentRepository.save(appointmentTime);
    }

    public List<AppointmentTime> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    @Transactional
    public void deleteById(Long id) {
        bookingRepository.deleteByAppointmentTimeId(id);
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentTime> findAllAppointmentsByPatientId(Long userId) {
        return appointmentRepository.findByPatientId(userId);
    }

    public List<AppointmentTime> findAllAppointmentsByDoctorId(Long id) {
        return  appointmentRepository.findByDoctorId(id);
    }
}
