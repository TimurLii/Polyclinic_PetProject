package com.example.polyclinic_petproject.repository;

import com.example.polyclinic_petproject.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
