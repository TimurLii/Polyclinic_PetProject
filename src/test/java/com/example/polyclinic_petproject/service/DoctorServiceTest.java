package com.example.polyclinic_petproject.service;

import com.example.polyclinic_petproject.entity.Doctor;
import com.example.polyclinic_petproject.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    private DoctorService doctorService;

    @Test
    public void testFindDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setFullName("Sergeev I.I.");
        doctorRepository.save(doctor);
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Optional<Doctor> result = doctorService.findById(1L);
        assertEquals("Sergeev I.I.", result.get().getFullName());
        Mockito.verify(doctorRepository, Mockito.times(1)).findById(1L);
    }
}