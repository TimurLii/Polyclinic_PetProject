package com.example.polyclinic_petproject.controller;

import com.example.polyclinic_petproject.model.AppointmentTime;
import com.example.polyclinic_petproject.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentTime createAppointment(@RequestBody AppointmentTime appointmentTime) {
        return appointmentService.saveAppointment(appointmentTime);
    }

    @GetMapping
    public List<AppointmentTime> getAppointments() {
        return appointmentService.getAppointments();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}