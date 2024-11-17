package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentTime, Long> {
    }

