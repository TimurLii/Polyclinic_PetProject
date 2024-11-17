package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.model.AppointmentTime;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentTime createAppointment(AppointmentTime appointmentTime) {
        return appointmentRepository.save(appointmentTime);
    }

    public List<AppointmentTime> getAppointments() {
        return appointmentRepository.findAll();
    }

    // Другие методы, например, для проверки доступных временных слотов
}
