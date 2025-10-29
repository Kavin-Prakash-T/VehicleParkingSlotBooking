package com.example.ParkingSlotBooking.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ParkingSlotBooking.entity.Booking;
import com.example.ParkingSlotBooking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	public static class CreateBookingRequest {
		public Long userId;
		public Long slotId;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		public LocalDateTime startTime;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		public LocalDateTime endTime;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Booking createBooking(@RequestBody CreateBookingRequest req) {
		Booking created = bookingService.createBooking(req.userId, req.slotId, req.startTime, req.endTime);
		return created;
	}

	@GetMapping
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@GetMapping("/user/{userId}")
	public List<Booking> getBookingsForUser(@PathVariable Long userId) {
		return bookingService.getBookingsForUser(userId);
	}

	@GetMapping("/{id}")
	public Booking getBooking(@PathVariable Long id) {
		Booking b = bookingService.getBooking(id);
		if (b == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
		return b;
	}

	@PatchMapping("/{id}/end")
	public Booking endBooking(@PathVariable Long id) {
		Booking b = bookingService.endBooking(id);
		return b;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelBooking(@PathVariable Long id) {
		bookingService.cancelBooking(id);
	}

}
