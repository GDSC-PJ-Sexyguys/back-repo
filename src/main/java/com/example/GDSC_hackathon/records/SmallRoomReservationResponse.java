package com.example.GDSC_hackathon.records;

import java.time.LocalDateTime;

public record SmallRoomReservationResponse(
        Long reservationId,
        Integer studentId,
        String buildingName,
        String roomName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer partySize
) {}