package com.example.polyclinic_petproject.entity.dto;

import com.example.polyclinic_petproject.enums.AppointmentTimeEnum;
import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.entity.Patient;
import lombok.Data;

@Data
public class AppointmentPageDto {

    private Long id;

    private AppointmentTimeEnum timeEnum;

    private Patient patient;
    private Doctor doctor;
}
