package com.example.polyclinic_petproject;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import com.example.polyclinic_petproject.entity.Booking;
import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import com.example.polyclinic_petproject.repository.BookingRepository;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import com.example.polyclinic_petproject.repository.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class PolyclinicPetProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PolyclinicPetProjectApplication.class, args);

        DoctorRepository doctorRepository = context.getBean(DoctorRepository.class);
        PatientRepository patientRepository = context.getBean(PatientRepository.class);
        AppointmentRepository appointmentTimeRepository = context.getBean(AppointmentRepository.class);
        BookingRepository bookingRepository = context.getBean(BookingRepository.class);

        AppointmentTime appointmentTime = null;
        Patient patient;
        patient = new Patient();
        patient.setFullName("Patient1");
        patient.setAge(1);
        patient.setContactDetails("Contact_details1");
        patient.setGender("Man");
        patient.setLogin("user");
        patient.setPassword("$2a$12$/SaF9KwHGITz0ZfgCEPELuYn4sRMkC7iRxVYKFwJDe.FuV//X5a7S");
        patientRepository.save(patient);
        for (int i = 0; i < 5; i++) {
            Doctor doctor = new Doctor();
            doctor.setFullName("Doctor:" + i);
            doctor.setSpecialty("Specialty" + i);
            doctor.setLogin("doc");
            doctor.setPassword("$2a$12$0MOXAH6SWIldo9J2yJBvm.aSSLBI2ffH/eOe.W8zUZiRINFoJwMbe");
            doctorRepository.save(doctor);

            appointmentTime = new AppointmentTime();
            appointmentTime.setDoctor(doctor);
            appointmentTime.setPatient(patient);
            appointmentTimeRepository.save(appointmentTime);

            Booking booking = new Booking();
            booking.setTimeEnum(AppointmentTimeEnum.returnRandomTime());
            booking.setLocalDate(LocalDate.of(2024, 11, 20));
            booking.setAppointmentTime(appointmentTime);
            booking.setStatus(Booking.BookingStatus.BUSY);
            bookingRepository.save(booking);


        }
        Doctor doctor = new Doctor();
        doctor.setFullName("Doctor:" );
        doctor.setSpecialty("Specialty" );
        doctor.setLogin("doc");
        doctor.setPassword("$2a$12$8dHHhksStUSNKFZ3zhs2guyh7w9baVHPMLL84TSvmcx5X8jGx8Sae");
        doctorRepository.save(doctor);

    }

}



