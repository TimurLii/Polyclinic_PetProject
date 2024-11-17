package com.example.polyclinic_petproject;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PolyclinicPetProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PolyclinicPetProjectApplication.class, args);

        DoctorRepository doctorRepository = context.getBean(DoctorRepository.class);

        for (int i = 0; i < 5; i++) {
            Doctor doctor = new Doctor();
            doctor.setFullName("Doctor:" + i);
            doctor.setSpecialty("Specialty" + i);
            doctorRepository.save(doctor);
        }
    }
}


