package com.example.GDSC_hackathon.controller;

import com.example.GDSC_hackathon.records.SmallRoomReservationRequest;
import com.example.GDSC_hackathon.records.SmallRoomReservationResponse;
import com.example.GDSC_hackathon.records.SmallRoomUsageResponse;
import com.example.GDSC_hackathon.service.SmallRoomReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/small-room/reservations")
@RequiredArgsConstructor
public class SmallRoomReservationController {

    private final SmallRoomReservationService reservationService;

    @PostMapping
    public ResponseEntity<Long> makeReservation(@RequestBody SmallRoomReservationRequest request) {
        Long reservationId = reservationService.makeReservation(request);
        return ResponseEntity.ok(reservationId);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<SmallRoomReservationResponse> getReservation(@PathVariable Integer studentId) {
        SmallRoomReservationResponse response = reservationService.getReservationByStudentId(studentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SmallRoomReservationResponse>> getRoomReservations(
            @RequestParam String buildingName,
            @RequestParam String roomName) {
        List<SmallRoomReservationResponse> reservations =
                reservationService.getRoomReservations(buildingName, roomName);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/daily-schedule")
    public ResponseEntity<List<SmallRoomReservationResponse>> getDailyRoomSchedule(
            @RequestParam String buildingName,
            @RequestParam String roomName) {
        List<SmallRoomReservationResponse> schedule =
                reservationService.getDailyRoomSchedule(buildingName, roomName);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/history/{studentId}")
    public ResponseEntity<List<SmallRoomReservationResponse>> getStudentReservations(
            @PathVariable Integer studentId) {
        List<SmallRoomReservationResponse> history =
                reservationService.getStudentReservations(studentId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/building/{buildingName}/status")
    public ResponseEntity<SmallRoomUsageResponse> getBuildingRoomStatus(
            @PathVariable String buildingName) {
        SmallRoomUsageResponse status = reservationService.getBuildingRoomStatus(buildingName);
        return ResponseEntity.ok(status);
    }
}