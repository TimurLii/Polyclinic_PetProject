package com.example.polyclinic_petproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DoctorDetailService doctorDetailService;
    private final PatientDetailsService patientDetailsService;

    @Autowired
    public CustomUserDetailsService(DoctorDetailService doctorDetailService,
                                    PatientDetailsService patientDetailsService) {
        this.doctorDetailService = doctorDetailService;
        this.patientDetailsService = patientDetailsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;
        try {
            user = doctorDetailService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // Игнорируем исключение, продолжаем выполнение
        }

        if (user == null) {
            user = patientDetailsService.loadUserByUsername(username);
            System.out.println(user);
            //FIXME добавить user.isEnabled(true)
        }
        return user;
    }
}
