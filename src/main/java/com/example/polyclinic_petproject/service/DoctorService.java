package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }
    @Transactional
    public void deleteById(Long id) {
        appointmentRepository.deleteByDoctorId(id);
        doctorRepository.deleteById(id);
    }


}