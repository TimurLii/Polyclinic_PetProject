package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found with username: " + username));
        return new DoctorDetails(doctor); // Возвращаем объект DoctorDetails
    }
}