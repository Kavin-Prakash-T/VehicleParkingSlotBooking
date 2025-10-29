package com.example.ParkingSlotBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ParkingSlotBooking.entity.Booking;
import com.example.ParkingSlotBooking.entity.Payment;
import com.example.ParkingSlotBooking.repository.BookingRepository;
import com.example.ParkingSlotBooking.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public Payment createPayment(Long bookingId, String method) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        double amount = booking.getAmount();
        if (amount <= 0 && booking.getStartTime() != null && booking.getEndTime() != null && booking.getSlot() != null) {
            long minutes = java.time.Duration.between(booking.getStartTime(), booking.getEndTime()).toMinutes();
            double hours = Math.max(0, minutes / 60.0);
            amount = Math.round(hours * booking.getSlot().getPricePerHour() * 100.0) / 100.0;
        }

        Payment p = new Payment();
        p.setBookingId(bookingId);
        p.setAmount(amount);
        p.setMethod(method == null ? "unknown" : method);
        p.setStatus("paid"); 
        booking.setStatus("paid");
        bookingRepository.save(booking);

        return paymentRepository.save(p);
    }

    public List<Payment> listPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}

