package com.example.ParkingSlotBooking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Slot {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long slotId;

    private String type;
    private String status;
    private double pricePerHour;
    private String location;

    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}

