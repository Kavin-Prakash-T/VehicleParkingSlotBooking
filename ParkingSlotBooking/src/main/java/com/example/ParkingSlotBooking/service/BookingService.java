package com.example.ParkingSlotBooking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ParkingSlotBooking.entity.Booking;
import com.example.ParkingSlotBooking.entity.Slot;
import com.example.ParkingSlotBooking.entity.User;
import com.example.ParkingSlotBooking.repository.BookingRepository;
import com.example.ParkingSlotBooking.repository.SlotRepository;
import com.example.ParkingSlotBooking.repository.UserRepository;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final SlotRepository slotRepository;

	@Autowired
	public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
			SlotRepository slotRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.slotRepository = slotRepository;
	}

	public Booking createBooking(Long userId, Long slotId, LocalDateTime startTime, LocalDateTime endTime) {
		User user = userRepository.findById(userId).orElse(null);
		Slot slot = slotRepository.findById(slotId).orElse(null);

		List<Booking> existing = bookingRepository.findAll();
		for (Booking b : existing) {
			if (b.getSlot() != null && b.getSlot().getSlotId().equals(slotId)) {
				if (b.getStatus() != null && (b.getStatus().equalsIgnoreCase("booked")
						|| b.getStatus().equalsIgnoreCase("ongoing"))) {
					LocalDateTime s1 = b.getStartTime();
					LocalDateTime e1 = b.getEndTime();
					if (s1 == null || e1 == null) continue;
					boolean overlap = !(endTime.isBefore(s1) || startTime.isAfter(e1));
					if (overlap) {
						return null;
					}
				}
			}
		}

		Booking booking = new Booking();
		booking.setUser(user);
		booking.setSlot(slot);
		booking.setStartTime(startTime);
		booking.setEndTime(endTime);
		booking.setStatus("booked");

		double hours = Math.max(0, Duration.between(startTime, endTime).toMinutes() / 60.0);
		double amount = Math.round(hours * slot.getPricePerHour() * 100.0) / 100.0;
		booking.setAmount(amount);

		slot.setStatus("occupied");
		slotRepository.save(slot);

		return bookingRepository.save(booking);
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public List<Booking> getBookingsForUser(Long userId) {
		return bookingRepository.findAll().stream()
				.filter(b -> b.getUser() != null && b.getUser().getUserId().equals(userId)).toList();
	}

	public Booking getBooking(Long id) {
		return bookingRepository.findById(id).orElse(null);
	}

	public Booking endBooking(Long id) {
		Booking b = bookingRepository.findById(id).orElse(null);
		if (b == null) return null;
		LocalDateTime now = LocalDateTime.now();
		if (b.getStartTime() != null && now.isBefore(b.getStartTime())) {
			return null;
		}
		b.setEndTime(now);
		b.setStatus("ended");

		double hours = Math.max(0, Duration.between(b.getStartTime(), now).toMinutes() / 60.0);
		double amount = Math.round(hours * b.getSlot().getPricePerHour() * 100.0) / 100.0;
		b.setAmount(amount);

		Slot slot = b.getSlot();
		if (slot != null) {
			slot.setStatus("available");
			slotRepository.save(slot);
		}

		return bookingRepository.save(b);
	}

	public boolean cancelBooking(Long id) {
		Booking b = bookingRepository.findById(id).orElse(null);
		if (b == null) return false;
		LocalDateTime now = LocalDateTime.now();
		if (b.getStartTime() != null && now.isAfter(b.getStartTime())) {
			return false;
		}

		Slot slot = b.getSlot();
		if (slot != null) {
			slot.setStatus("available");
			slotRepository.save(slot);
		}

		bookingRepository.delete(b);
		return true;
	}

}
