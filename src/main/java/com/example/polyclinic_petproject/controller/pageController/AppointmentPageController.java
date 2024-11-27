package com.example.polyclinic_petproject.controller.pageController;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.impl.DoctorUserDetails;
import com.example.polyclinic_petproject.impl.PatientUserDetails;
import com.example.polyclinic_petproject.service.AppointmentService;
import com.example.polyclinic_petproject.service.BookingService;
import com.example.polyclinic_petproject.service.DoctorService;
import com.example.polyclinic_petproject.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentPageController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final BookingService bookingService;

    public AppointmentPageController(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, BookingService bookingService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.bookingService = bookingService;
    }

    @GetMapping()

    public String getAllAppointments(Model model
            , @AuthenticationPrincipal PatientUserDetails patientUserDetails
            , @AuthenticationPrincipal DoctorUserDetails doctorUserDetails) {
        System.out.println(patientUserDetails == null);
        System.out.println(doctorUserDetails == null);
        List<AppointmentTime> appointments =null;
        if (patientUserDetails == null) {
            appointments = appointmentService.findAllAppointmentsByDoctorId(doctorUserDetails.getId());
        }
        else{
            appointments = appointmentService.findAllAppointmentsByPatientId(patientUserDetails.getId());
        }


        model.addAttribute("appointments", appointments);

        List<Booking> allBookings = bookingService.getAllBookings();


        model.addAttribute("bookings", allBookings);

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

    @GetMapping("/createAgain")
    public String createAppointmentAgain(Model model) {

        model.addAttribute("appointmentTime", new AppointmentTime());

        model.addAttribute("appointmentTimeEnum", AppointmentTimeEnum.values());

        List<Doctor> allDoctors = doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);

        return "createAppointmentPageAgain";
    }

    @PostMapping()
    public String createAppointment(@ModelAttribute AppointmentTime appointmentTime,
                                    @RequestParam("selectedDoctorId") Long selectedDoctorId,
                                    @RequestParam("selectedTimeEnum") String selectedTimeEnum,
                                    @RequestParam("dataSelected") LocalDate dataSelected,
                                    @AuthenticationPrincipal PatientUserDetails userDetails) {

        LocalDate currentDate = dataSelected;
        String currentTime = selectedTimeEnum;

        Patient patient = userDetails.getPatient();

        if (patient.isEnabled()) {
            appointmentTime.setPatient(patient);
        } else {
            return "redirect:/appointment/error";
        }

        Doctor doctor = doctorService.findById(selectedDoctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointmentTime.setDoctor(doctor);

        appointmentService.saveAppointment(appointmentTime);

        Booking booking = new Booking();
        booking.setLocalDate(currentDate);
        booking.setTimeEnum(AppointmentTimeEnum.fromDisplayName(currentTime));
        booking.setAppointmentTime(appointmentTime);

        if (!isFreeAppointmentTime(booking) &&
                !isDoctorBusy(currentDate, appointmentTime, AppointmentTimeEnum.fromDisplayName(currentTime))) {

            bookingService.saveBooking(booking);
            return "redirect:/appointment";
        }

        appointmentService.deleteById(appointmentTime.getId());
        return "redirect:/appointment/createAgain";
    }

    private boolean isFreeAppointmentTime(Booking booking) {
        return bookingService.existsByDateAndTime(booking.getLocalDate(), booking.getTimeEnum());

    }

    private boolean isDoctorBusy(LocalDate localDate, AppointmentTime appointmentTime, AppointmentTimeEnum appointmentTimeEnum) {
        return bookingService.existsByLocalDateAndAppointmentTimeAndTimeEnum(localDate, appointmentTime, appointmentTimeEnum);
    }
}
