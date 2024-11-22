package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class DoctorDetails implements UserDetails {
    private final Doctor doctor;

    public DoctorDetails(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return doctor.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return doctor.getPassword();
    }

    @Override
    public String getUsername() {
        return doctor.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Настройте по необходимости
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Настройте по необходимости
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Настройте по необходимости
    }

    @Override
    public boolean isEnabled() {
        return true; // Настройте по необходимости
    }
}