package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.service.AppointmentService;
import com.example.polyclinic_petproject.service.DoctorService;
import com.example.polyclinic_petproject.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentPageController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public AppointmentPageController(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
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

        model.addAttribute("appointmentTime", new AppointmentTime());

        model.addAttribute("appointmentTimeEnum", AppointmentTimeEnum.values());

        List<Doctor> allDoctors = doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);

        return "createAppointmentPage";
    }

    @PostMapping()
    public String createAppointment(@ModelAttribute AppointmentTime appointmentTime,
                                    @RequestParam("selectedDoctorId") Long selectedDoctorId,
                                    @RequestParam("selectedTimeEnum") String selectedTimeEnum,
                                    @AuthenticationPrincipal UserDetails userDetails) {

        Patient patient = patientService.findByFullName(userDetails.getUsername());
        if (patient.isEnabled()) {
            appointmentTime.setPatient(patient);
        }

        Doctor doctor = doctorService.findById(selectedDoctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointmentTime.setDoctor(doctor);

        appointmentTime.setTimeEnum(AppointmentTimeEnum.fromDisplayName(selectedTimeEnum));


        appointmentService.saveAppointment(appointmentTime);
        return "redirect:/appointment";
    }

}
