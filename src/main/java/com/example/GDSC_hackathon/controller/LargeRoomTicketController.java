package com.example.GDSC_hackathon.controller;

import com.example.GDSC_hackathon.records.LargeRoomTicketRequest;
import com.example.GDSC_hackathon.records.LargeRoomTicketResponse;
import com.example.GDSC_hackathon.service.LargeRoomTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/large-room/tickets")
@RequiredArgsConstructor
public class LargeRoomTicketController {

    private final LargeRoomTicketService largeRoomTicketService;

    @PostMapping
    public ResponseEntity<Long> issueTicket(@RequestBody LargeRoomTicketRequest request) {
        Long ticketId = largeRoomTicketService.issueTicket(request);
        return ResponseEntity.ok(ticketId);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<LargeRoomTicketResponse> getTicket(@PathVariable Integer studentId) {
        LargeRoomTicketResponse response = largeRoomTicketService.getTicketByStudentId(studentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Integer>> getOccupiedSeats(
            @RequestParam String buildingName,
            @RequestParam String roomName) {
        List<Integer> occupiedSeats = largeRoomTicketService.getOccupiedSeats(buildingName, roomName);
        return ResponseEntity.ok(occupiedSeats);
    }
}