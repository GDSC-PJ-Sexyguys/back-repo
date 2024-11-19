package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.SmallRoomReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmallRoomReservationRepository extends JpaRepository<SmallRoomReservationHistory, Long> {
    Optional<SmallRoomReservationHistory> findByStudentId(Integer studentId);

    List<SmallRoomReservationHistory> findByBuildingNameAndRoomNameAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            String buildingName, String roomName, LocalDateTime endTime, LocalDateTime startTime);

    List<SmallRoomReservationHistory> findByStudentIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Integer studentId, LocalDateTime endTime, LocalDateTime startTime);

    List<SmallRoomReservationHistory> findAllByEndTimeBefore(LocalDateTime now);

    List<SmallRoomReservationHistory> findByBuildingNameAndRoomNameAndStartTimeGreaterThanEqualOrderByStartTimeAsc(
            String buildingName, String roomName, LocalDateTime startTime);

    List<SmallRoomReservationHistory> findByBuildingNameAndRoomNameAndStartTimeBetweenOrderByStartTimeAsc(
            String buildingName,
            String roomName,
            LocalDateTime dayStart,
            LocalDateTime dayEnd);

    List<SmallRoomReservationHistory> findByBuildingNameAndStartTimeBeforeAndEndTimeAfter(
            String buildingName,
            LocalDateTime now,
            LocalDateTime sameNow);

    // 학생의 강의실 예약 내역 조회
    List<SmallRoomReservationHistory> findByStudentIdOrderByStartTimeDesc(Integer studentId);

}