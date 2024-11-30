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
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
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

//    @GetMapping()
//    @Transactional
//    public String getAllAppointments(Model model
//            , @AuthenticationPrincipal PatientUserDetails patientUserDetails
//            , @AuthenticationPrincipal DoctorUserDetails doctorUserDetails) {
//
//        List<AppointmentTime> appointments = getAppointmentTimes(patientUserDetails, doctorUserDetails);
//        // возвращается список всех записей для авторизованного пользователя
//
//
//        model.addAttribute("appointments", appointments);
//
//        List<Booking> allBookings = bookingService.getAllBookings();
//
//
//        model.addAttribute("bookings", allBookings);
//
//        return "appointmentsPage";
//    }
@GetMapping()
@Transactional
public String getAllAppointments(Model model,
                                 @AuthenticationPrincipal PatientUserDetails patientUserDetails,
                                 @AuthenticationPrincipal DoctorUserDetails doctorUserDetails) {
    List<AppointmentTime> appointments = getAppointmentTimes(patientUserDetails, doctorUserDetails);

    // Инициализируйте ленивые коллекции, если необходимо
    appointments.forEach(appointment -> Hibernate.initialize(appointment.getBookings()));

    model.addAttribute("appointments", appointments);
    List<Booking> allBookings = bookingService.getAllBookings();
    model.addAttribute("bookings", allBookings);

    return "appointmentsPage";
}

    // создание страницы, на которую передаётся ENUM со временем, и список всех врачей
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

    /***
     * Метод отвечающие за создание новой записи и сохранении ее в базе
     * @param appointmentTime новая запись
     * @param selectedDoctorId врач, которого выбрал пользователь на странице http://localhost:8080/appointment/create
     * @param selectedTimeEnum время выбранное пользователем
     * @param dataSelected дата выбранная пользователем
     * @param userDetails авторизованный пользователь
     * @return если врач, время и дата свободны происходит редирект на страницу со всеми записями пользователя
     * если что-либо занято происходит редирект на страницу создания новой записи
     */
    @PostMapping()
    @Transactional
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

        // Метод отвечающий за создание нового бронирования
        Booking booking = createBooking(appointmentTime, selectedTimeEnum, dataSelected);

        // проверка свободно ли выбранные дата, время и доктор
        if (!isFreeAppointmentTime(booking) &&
                !isDoctorBusy(dataSelected, appointmentTime, AppointmentTimeEnum.fromDisplayName(selectedTimeEnum))) {

            bookingService.saveBooking(booking);
            return "redirect:/appointment";
        }

        appointmentService.deleteById(appointmentTime.getId());
        return "redirect:/appointment/createAgain";
    }

    // Метод отвечающий за создание нового бронирования
    private Booking createBooking(AppointmentTime appointmentTime, String selectedTimeEnum, LocalDate dataSelected) {
        Booking booking = new Booking();
        booking.setLocalDate(dataSelected);
        booking.setTimeEnum(AppointmentTimeEnum.fromDisplayName(selectedTimeEnum));
        booking.setAppointmentTime(appointmentTime);
        booking.setStatus(Booking.BookingStatus.BUSY);
        return booking;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
    }

    //     Проверка есть ли данные время и дата в таблице
    private boolean isFreeAppointmentTime(Booking booking) {
        return bookingService.existsByDateAndTime(booking.getLocalDate(), booking.getTimeEnum());

    }

    //Проверка занят ли доктор в это время и дату
    private boolean isDoctorBusy(LocalDate localDate, AppointmentTime appointmentTime, AppointmentTimeEnum appointmentTimeEnum) {
        return bookingService.existsByLocalDateAndAppointmentTimeAndTimeEnum(localDate, appointmentTime, appointmentTimeEnum);
    }

    private List<AppointmentTime> getAppointmentTimes(PatientUserDetails patientUserDetails, DoctorUserDetails doctorUserDetails) {
        List<AppointmentTime> appointments;
        if (patientUserDetails == null) {
            appointments = appointmentService.findAllAppointmentsByDoctorId(doctorUserDetails.getId());
        } else {
            appointments = appointmentService.findAllAppointmentsByPatientId(patientUserDetails.getId());
        }
        return appointments;
    }
}
