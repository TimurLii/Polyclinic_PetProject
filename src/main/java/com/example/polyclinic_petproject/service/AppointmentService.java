package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentTime saveAppointment(AppointmentTime appointmentTime) {
        return appointmentRepository.save(appointmentTime);
    }

    public List<AppointmentTime> getAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }


//    public boolean isTimeAvailable(LocalDateTime time) {
//        List<AppointmentTime> appointments = appointmentRepository.findAll();
//        return appointments.stream().noneMatch(a -> a.getAppointmentTime().isEqual(time));
//    }

    // Другие методы, например, для проверки доступных временных слотов
}
