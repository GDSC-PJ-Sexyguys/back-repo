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
public class SmallRoomReservationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer studentId;
    private String buildingName;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer partySize;

    @Builder
    public SmallRoomReservationHistory(Integer studentId, String buildingName,
                                       String roomName, Integer partySize, LocalDateTime startTime,
                                       LocalDateTime endTime) {
        this.studentId = studentId;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.partySize = partySize;
    }
}