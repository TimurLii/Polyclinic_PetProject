package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.service.AppointmentService;
import com.example.polyclinic_petproject.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentPageController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;



    public AppointmentPageController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;

    }

    @GetMapping()
    public String getAllAppointments(Model model) {
        List<AppointmentTime> appointments = appointmentService.getAppointments();
        model.addAttribute("appointments", appointments);
        return "appointmentsPage";
    }


    @GetMapping("/create")
    public String createAppointmentTime(Model model) {
        model.addAttribute("appointmentTimeEnum", AppointmentTimeEnum.values());
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);
        return "createAppointmentPage";
    }

    @PostMapping()
    public String createAppointment(@ModelAttribute AppointmentTime appointmentTime) {
        appointmentService.saveAppointment(appointmentTime);
        return "redirect:/appointment";
    }
}
