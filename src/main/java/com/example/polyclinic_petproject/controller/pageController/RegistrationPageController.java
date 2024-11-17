package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationPageController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String showRegistrationPage(Model model) { // Добавьте параметр Model
        System.out.println("1");
        model.addAttribute("patient", new Patient()); // Добавьте новый объект Patient в модель
        return "registrationFormPage"; // Возврат шаблона
    }

    @PostMapping("/registration")
    public String registerPatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setActive(true);
        patientRepository.save(patient);
        return "redirect:/login";
    }
}