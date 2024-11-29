package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Patient;
import com.example.polyclinic_petproject.repository.AppointmentRepository;
import com.example.polyclinic_petproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Object> findById(Long id) {
        return Optional.of(patientRepository.findById(id));
    }

    @Transactional
    public void deleteById(Long id) {
        appointmentRepository.deleteByPatientId(id);
        patientRepository.deleteById(id);
    }

    public Patient findByFullName(String fullName) {
        return patientRepository.findByFullName(fullName);
    }


}
