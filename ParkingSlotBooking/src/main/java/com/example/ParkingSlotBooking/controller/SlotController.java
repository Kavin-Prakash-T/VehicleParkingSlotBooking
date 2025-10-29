package com.example.ParkingSlotBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ParkingSlotBooking.entity.Slot;
import com.example.ParkingSlotBooking.service.SlotService;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping
    public List<Slot> listSlots() {
        return slotService.listSlots();
    }

    @GetMapping("/{id}")
    public Slot getSlot(@PathVariable Long id) {
        return slotService.getSlot(id);  
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Slot createSlot(@RequestBody Slot slot) {
        return slotService.createSlot(slot);
    }

    @PutMapping("/{id}")
    public Slot updateSlot(@PathVariable Long id, @RequestBody Slot slot) {
        return slotService.updateSlot(id, slot);
    }

    @PatchMapping("/{id}/status")
    public Slot updateStatus(@PathVariable Long id, @RequestParam String status) {
        return slotService.updateStatus(id, status);
    }

}
