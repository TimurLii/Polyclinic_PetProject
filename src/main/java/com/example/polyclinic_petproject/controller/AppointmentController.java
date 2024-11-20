package com.example.polyclinic_petproject.controller;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.service.AppointmentService;
import com.example.polyclinic_petproject.service.AppointmentTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentTimeService appointmentTimeService;


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
    @GetMapping("/appointment")
    public String showAppointmentForm(Model model) {
        List<AppointmentTime> availableTimes = appointmentTimeService.getAvailableAppointmentTimes();
        model.addAttribute("availableTimes", availableTimes);
        return "appointmentForm"; // имя вашего шаблона
    }
}