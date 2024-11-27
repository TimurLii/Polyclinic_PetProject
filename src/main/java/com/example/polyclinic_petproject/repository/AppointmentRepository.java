package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentTime, Long> {

    List<AppointmentTime> findAllAppointmentsByDoctor_Id(Long doctorId);
    List<AppointmentTime> findByPatientId(Long userId);
}

