package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.model.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentTime, Long> {
    }

