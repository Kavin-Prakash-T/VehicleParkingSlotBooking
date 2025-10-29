package com.example.ParkingSlotBooking.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-bookings")
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    @JsonBackReference("slot-bookings")
    private Slot slot;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private double amount;
    private LocalDateTime createdAt = LocalDateTime.now();
}

