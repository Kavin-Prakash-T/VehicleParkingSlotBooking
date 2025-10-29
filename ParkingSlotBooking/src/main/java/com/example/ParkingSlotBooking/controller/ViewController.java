package com.example.ParkingSlotBooking.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ParkingSlotBooking.entity.Slot;
import com.example.ParkingSlotBooking.service.BookingService;
import com.example.ParkingSlotBooking.service.PaymentService;
import com.example.ParkingSlotBooking.service.UserService;
import com.example.ParkingSlotBooking.entity.User;
import com.example.ParkingSlotBooking.service.SlotService;

@Controller
@RequestMapping
public class ViewController {

    private final SlotService slotService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Autowired
    public ViewController(SlotService slotService, BookingService bookingService, PaymentService paymentService, UserService userService) {
        this.slotService = slotService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.userService = userService;
    }
    @GetMapping("/slots")
    public String slotsPage(@RequestParam(required = false) String status,@RequestParam(required = false) String type, @RequestParam(required = false) String location,Model model) {
        List<Slot> slots = slotService.listSlots();
        if (status != null && !status.isBlank()) {
            slots = slots.stream().filter(s -> s.getStatus() != null && s.getStatus().equalsIgnoreCase(status)).toList();
        }
        if (type != null && !type.isBlank()) {
            slots = slots.stream().filter(s -> s.getType() != null && s.getType().toLowerCase().contains(type.toLowerCase())).toList();
        }
        if (location != null && !location.isBlank()) {
            slots = slots.stream().filter(s -> s.getLocation() != null && s.getLocation().toLowerCase().contains(location.toLowerCase())).toList();
        }
        model.addAttribute("slots", slots);
        return "slots";
    }

    @GetMapping("/bookings")
    public String bookingsPage(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/payments")
    public String paymentsPage(Model model) {
        model.addAttribute("payments", paymentService.listPayments());
        return "payments";
    }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/register")
    public String registerPage() { return "register"; }



    @PostMapping(path = "/register")
    public String registerForm(@RequestParam String name, @RequestParam String email, @RequestParam String password,
            @RequestParam(required = false) String phoneNumber) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhoneNumber(phoneNumber);
        userService.registerUser(u);
        return "redirect:/login";
    }

    @PostMapping(path = "/login")
    public String loginForm(@RequestParam String email, @RequestParam String password) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        String res = userService.loginUser(u);
        if (res != null && res.toLowerCase().contains("login successful")) {
            return "redirect:/slots";
        }
        return "redirect:/login?error";
    }

}
