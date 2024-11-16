package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
