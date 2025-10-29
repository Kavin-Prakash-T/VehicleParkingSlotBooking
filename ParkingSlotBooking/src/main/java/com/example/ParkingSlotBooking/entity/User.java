package com.example.ParkingSlotBooking.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;*/

}