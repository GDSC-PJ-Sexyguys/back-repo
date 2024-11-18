package com.example.GDSC_hackathon.controller;

import com.example.GDSC_hackathon.domain.Seat;
import com.example.GDSC_hackathon.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.saveSeat(seat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeat(@PathVariable Long id) {
        return seatService.getSeatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seat) {
        if (!seatService.getSeatById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        seat.setId(id);
        return ResponseEntity.ok(seatService.saveSeat(seat));
    }
}