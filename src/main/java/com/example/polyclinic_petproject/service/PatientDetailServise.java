package com.example.polyclinic_petproject.service;


import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.repository.PatientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientDetailServise implements UserDetailsService {

    private final PatientRepository patientRepository;

    public PatientDetailServise(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = (Patient) patientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return User.builder()
                .username(patient.getUsername())
                .password(patient.getPassword())
                .roles(patient.getRole()) // ROLE_PATIENT
                .build();
    }
}
