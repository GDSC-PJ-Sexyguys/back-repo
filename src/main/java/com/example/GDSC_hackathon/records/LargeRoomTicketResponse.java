package com.example.GDSC_hackathon.records;

import java.time.LocalDateTime;

public record LargeRoomTicketResponse(
        Long ticketId,
        Integer studentId,
        String buildingName,
        String roomName,
        Integer seatNumber,
        LocalDateTime issuedAt,
        LocalDateTime endTime
) {}