package com.example.GDSC_hackathon.records;


public record LargeRoomTicketRequest(
        Integer bookerStudentId,
        String buildingName,
        String roomName,
        Integer seatNumber
) {
}
