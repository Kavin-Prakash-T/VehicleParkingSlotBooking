
package com.example.ParkingSlotBooking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference("slot-bookings")
    private List<Booking> bookings;

}

