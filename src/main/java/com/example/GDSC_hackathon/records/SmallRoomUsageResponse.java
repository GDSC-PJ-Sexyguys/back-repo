package com.example.GDSC_hackathon.records;

import java.util.List;

public record SmallRoomUsageResponse(
        String buildingName,
        List<RoomStatus> rooms
) {
    public record RoomStatus(
            String roomName,
            boolean inUse,
            Integer currentUserId
    ) {}
}