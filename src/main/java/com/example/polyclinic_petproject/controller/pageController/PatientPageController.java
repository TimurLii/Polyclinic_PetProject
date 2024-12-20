package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientPageController {
    private final PatientService patientService;

    public PatientPageController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping()
    public String getAllPatients(Model model){
        List<Patient> allPatients = patientService.getAllPatients();
        model.addAttribute("allPatients", allPatients);
        return "patientsPage";
    }
    @GetMapping("/create")
    public String createPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "createPatient-Page";
    }

    @PostMapping()
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patient";
    }
}
