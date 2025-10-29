package com.example.ParkingSlotBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ParkingSlotBooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
