package com.example.GDSC_hackathon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class LargeLectureRoomTicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer studentId;

    private String buildingName;

    private String roomName;

    private Integer seatNumber;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    @Builder
    public LargeLectureRoomTicketHistory(Integer studentId, String buildingName, String roomName, Integer seatNumber) {
        this.studentId = studentId;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.seatNumber = seatNumber;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusHours(6);
    }
}
