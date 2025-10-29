package com.example.ParkingSlotBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ParkingSlotBooking.entity.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
	
}
