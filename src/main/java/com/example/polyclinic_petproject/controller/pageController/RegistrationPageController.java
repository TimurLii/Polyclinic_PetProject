package com.example.polyclinic_petproject.controller.pageController;


import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationPageController {
    @Autowired
    private PatientService patientService;

    @GetMapping()
    public String showRegistrationPage(Model model) { // Добавьте параметр Model
        model.addAttribute("patient", new Patient()); // Добавьте новый объект Patient в модель
        return "registrationFormPage"; // Возврат шаблона
    }

    @PostMapping()
    public String registerPatient(@ModelAttribute Patient patient) {
        if (patient != null) {
            patientService.savePatient(patient);
            System.out.println(patient);
            return "redirect:/loginPage";
        }else{
            return "registrationFormPage"; // Возврат шаблона
        }
    }
}