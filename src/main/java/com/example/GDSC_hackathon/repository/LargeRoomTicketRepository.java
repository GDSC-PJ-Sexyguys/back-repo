package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.LargeLectureRoomTicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LargeRoomTicketRepository extends JpaRepository<LargeLectureRoomTicketHistory, Long> {
    boolean existsByStudentId(Integer studentId);
    boolean existsBySeatNumberAndBuildingNameAndRoomName(Integer seatNumber, String buildingName, String roomName);
    Optional<LargeLectureRoomTicketHistory> findByStudentId(Integer studentId);
    List<LargeLectureRoomTicketHistory> deleteAllByEndTimeBefore(LocalDateTime endTime);
    // LargeRoomTicketRepository.java에 추가
    List<LargeLectureRoomTicketHistory> findByBuildingNameAndRoomName(String buildingName, String roomName);
}