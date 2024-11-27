package com.example.polyclinic_petproject.impl;

import com.example.polyclinic_petproject.entity.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PatientUserDetails implements UserDetails {
    private final Patient patient;

    public PatientUserDetails(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return patient.getRoles().stream()
                .map(role -> (GrantedAuthority) role::name)
                .toList();
    }

    @Override
    public String getPassword() {
        return patient.getPassword();
    }

    @Override
    public String getUsername() {
        return patient.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return patient.getId();
    }

    public Patient getPatient() {
        return patient;
    }
}
