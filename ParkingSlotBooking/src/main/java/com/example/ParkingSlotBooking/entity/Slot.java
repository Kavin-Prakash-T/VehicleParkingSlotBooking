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

/*package com.ecommerce.wishlist.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String notificationPreference;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-wishlist")
    private List<Wishlist> wishlistItems;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;
}
*/