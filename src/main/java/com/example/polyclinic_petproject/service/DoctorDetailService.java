package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.impl.DoctorUserDetails;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service

public class DoctorDetailService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doctor = (Doctor) doctorRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Collection<? extends GrantedAuthority> authorities = doctor.getRoles().stream()
                .map(role -> (GrantedAuthority) () -> role.name())
                .toList();
        System.out.println();
        return new DoctorUserDetails(doctor);
    }

}
