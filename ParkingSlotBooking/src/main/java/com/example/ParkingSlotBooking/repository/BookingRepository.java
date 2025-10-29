package com.example.ParkingSlotBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ParkingSlotBooking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
}
