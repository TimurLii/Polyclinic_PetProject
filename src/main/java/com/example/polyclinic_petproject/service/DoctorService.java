package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Object> findById(Long id) {
        return Optional.of(doctorRepository.findById(id));
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }


}