package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.impl.PatientUserDetails;
import com.example.polyclinic_petproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientDetailsService implements UserDetailsService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = (Patient) patientRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User  not found"));

        Collection<? extends GrantedAuthority> authorities = patient.getRoles().stream()
                .map(role -> (GrantedAuthority) role::name)
                .toList();

        return new PatientUserDetails(patient);
    }
}