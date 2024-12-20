package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Object> findByLogin(String username);

    Patient findByFullName (String fullName);
}
