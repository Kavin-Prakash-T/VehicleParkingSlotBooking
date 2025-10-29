package com.example.ParkingSlotBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ParkingSlotBooking.entity.Payment;
import com.example.ParkingSlotBooking.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public static class CreatePaymentRequest {
        public Long bookingId;
        public String method;
    }

    @PostMapping
    public Payment createPayment(@RequestBody CreatePaymentRequest req) {
        return paymentService.createPayment(req.bookingId, req.method);
    }

    @GetMapping
    public List<Payment> listPayments() {
        return paymentService.listPayments();
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }
}
 