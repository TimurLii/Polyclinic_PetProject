package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.model.Doctor;
import com.example.polyclinic_petproject.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorPageController {

    private final DoctorService doctorService;

    public DoctorPageController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public String getAllDoctors(Model model) {
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);
        return "doctorsPage";

    }

    @GetMapping("/create")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "createDoctor-Page";
    }

    @PostMapping()
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor";
    }
}
