package com.example.polyclinic_petproject.impl;

import com.example.polyclinic_petproject.entity.Doctor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class DoctorUserDetails implements UserDetails {
    private final Doctor doctor;

    public DoctorUserDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return doctor.getRoles().stream()
                .map(role -> (GrantedAuthority) role::name)
                .toList();
    }

    @Override
    public String getPassword() {
        return doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getLogin();
    }
    public Long getId() {
        return doctor.getId();
    }
}
