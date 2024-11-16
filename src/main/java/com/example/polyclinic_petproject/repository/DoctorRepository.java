package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
