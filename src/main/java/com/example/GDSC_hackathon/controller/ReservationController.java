package com.example.GDSC_hackathon.controller;

import com.example.GDSC_hackathon.domain.Reservation;
import com.example.GDSC_hackathon.domain.Seat;
import com.example.GDSC_hackathon.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation,
                                                         @RequestParam LocalDateTime startTime) {
        return ResponseEntity.ok(reservationService.saveReservation(reservation, startTime));
    }

    @GetMapping("/classroom/{classroomId}/active-seats")
    public ResponseEntity<List<Seat>> getActiveSeats(@PathVariable Long classroomId) {
        return ResponseEntity.ok(reservationService.getActiveSeats(classroomId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Reservation> getStudentReservation(@PathVariable Long studentId) {
        return reservationService.getReservationsByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/seat/{seatId}")
    public ResponseEntity<List<Reservation>> getSeatReservations(@PathVariable Long seatId) {
        return ResponseEntity.ok(reservationService.getReservationsBySeatId(seatId));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Reservation>> getClassroomReservations(@PathVariable Long classroomId) {
        return ResponseEntity.ok(reservationService.getReservationsByClassroomId(classroomId));
    }

    @DeleteMapping("/cleanup")
    public ResponseEntity<Void> cleanupPastReservations() {
        reservationService.deletePastReservations();
        return ResponseEntity.ok().build();
    }
}