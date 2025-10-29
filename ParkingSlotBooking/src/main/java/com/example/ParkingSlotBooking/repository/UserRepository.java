package com.example.ParkingSlotBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ParkingSlotBooking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
} 
