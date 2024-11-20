package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTimeRepository extends JpaRepository<AppointmentTime, Long> {
}
