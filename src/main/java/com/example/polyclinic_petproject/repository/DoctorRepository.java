package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByLogin(String login);

    void deleteDoctorById(Long id);
}
