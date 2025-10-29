package com.example.ParkingSlotBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ParkingSlotBooking.entity.Slot;
import com.example.ParkingSlotBooking.repository.SlotRepository;

@Service
public class SlotService {
	private final SlotRepository slotRepository;

	@Autowired
	public SlotService(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

	public List<Slot> listSlots() {
		return slotRepository.findAll();
	}

	public Slot getSlot(Long id) {
		return slotRepository.findById(id).orElse(null);
	}

	public Slot createSlot(Slot slot) {
		return slotRepository.save(slot);
	}

	public Slot updateSlot(Long id, Slot updated) {
		return slotRepository.findById(id).map(existing -> {
			existing.setType(updated.getType());
			existing.setStatus(updated.getStatus());
			existing.setPricePerHour(updated.getPricePerHour());
			existing.setLocation(updated.getLocation());
			return slotRepository.save(existing);}).orElse(null);
	}

	public Slot updateStatus(Long id, String status) {
		return slotRepository.findById(id).map(existing -> {
			existing.setStatus(status);
			return slotRepository.save(existing);}).orElse(null);
	}
}
