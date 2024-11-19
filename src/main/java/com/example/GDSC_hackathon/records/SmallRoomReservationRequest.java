package com.example.GDSC_hackathon.records;

import java.time.LocalDateTime;

public record SmallRoomReservationRequest(
        Integer bookerStudentId,
        String buildingName,
        String roomName,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}