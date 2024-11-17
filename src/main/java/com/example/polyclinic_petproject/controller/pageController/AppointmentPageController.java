package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.model.Doctor;
import com.example.polyclinic_petproject.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentPageController {

    private final DoctorService doctorService;

    public AppointmentPageController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping("/create")
    public String createAppointmentTime(Model model) {
        model.addAttribute("appointmentTimeEnum", AppointmentTimeEnum.values());
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);
        return "createAppointmentPage";
    }
}
