package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.SmallRoomReservationHistory;
import com.example.GDSC_hackathon.exception.GDSCException;
import com.example.GDSC_hackathon.records.SmallRoomReservationRequest;
import com.example.GDSC_hackathon.records.SmallRoomReservationResponse;
import com.example.GDSC_hackathon.records.SmallRoomUsageResponse;
import com.example.GDSC_hackathon.repository.SmallRoomReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.GDSC_hackathon.records.SmallRoomUsageResponse.*;

@Service
@RequiredArgsConstructor
public class SmallRoomReservationService {

    private final SmallRoomReservationRepository reservationRepository;

    @Transactional
    public Long makeReservation(SmallRoomReservationRequest request) {
        validateReservationTime(request.startTime(), request.endTime());
        deleteExpiredReservations();

        // 해당 시간대에 이미 예약된 강의실인지 확인
        List<SmallRoomReservationHistory> overlappingRoomReservations =
                reservationRepository.findByBuildingNameAndRoomNameAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        request.buildingName(),
                        request.roomName(),
                        request.endTime(),
                        request.startTime()
                );

        if (!overlappingRoomReservations.isEmpty()) {
            throw GDSCException.ROOM_ALREADY_RESERVED;
        }

        // 학생이 해당 시간대에 다른 예약을 했는지 확인
        List<SmallRoomReservationHistory> overlappingStudentReservations =
                reservationRepository.findByStudentIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        request.bookerStudentId(),
                        request.endTime(),
                        request.startTime()
                );

        if (!overlappingStudentReservations.isEmpty()) {
            throw GDSCException.ALREADY_HAS_RESERVATION;
        }

        SmallRoomReservationHistory reservation = SmallRoomReservationHistory.builder()
                .studentId(request.bookerStudentId())
                .buildingName(request.buildingName())
                .roomName(request.roomName())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();

        SmallRoomReservationHistory savedReservation = reservationRepository.save(reservation);
        return savedReservation.getId();
    }

    private void validateReservationTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        if (startTime.isBefore(now)) {
            throw GDSCException.INVALID_START_TIME;
        }
        if (endTime.isBefore(startTime)) {
            throw GDSCException.INVALID_END_TIME;
        }
        // 최대 예약 시간 제한 (예: 4시간)
        if (startTime.plusHours(4).isBefore(endTime)) {
            throw GDSCException.EXCEEDED_MAX_RESERVATION_TIME;
        }
    }

    @Transactional
    public void deleteExpiredReservations() {
        List<SmallRoomReservationHistory> expiredReservations =
                reservationRepository.findAllByEndTimeBefore(LocalDateTime.now());
        reservationRepository.deleteAll(expiredReservations);
    }

    public SmallRoomReservationResponse getReservationByStudentId(Integer studentId) {
        deleteExpiredReservations();
        SmallRoomReservationHistory reservation = reservationRepository.findByStudentId(studentId)
                .orElseThrow(() -> GDSCException.RESERVATION_NOT_FOUND);

        return new SmallRoomReservationResponse(
                reservation.getId(),
                reservation.getStudentId(),
                reservation.getBuildingName(),
                reservation.getRoomName(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }

    public List<SmallRoomReservationResponse> getRoomReservations(String buildingName, String roomName) {
        deleteExpiredReservations();
        return reservationRepository
                .findByBuildingNameAndRoomNameAndStartTimeGreaterThanEqualOrderByStartTimeAsc(
                        buildingName, roomName, LocalDateTime.now())
                .stream()
                .map(reservation -> new SmallRoomReservationResponse(
                        reservation.getId(),
                        reservation.getStudentId(),
                        reservation.getBuildingName(),
                        reservation.getRoomName(),
                        reservation.getStartTime(),
                        reservation.getEndTime()
                ))
                .toList();
    }

    public List<SmallRoomReservationResponse> getDailyRoomSchedule(String buildingName, String roomName) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        return reservationRepository
                .findByBuildingNameAndRoomNameAndStartTimeBetweenOrderByStartTimeAsc(
                        buildingName,
                        roomName,
                        startOfDay,
                        endOfDay)
                .stream()
                .map(reservation -> new SmallRoomReservationResponse(
                        reservation.getId(),
                        reservation.getStudentId(),
                        reservation.getBuildingName(),
                        reservation.getRoomName(),
                        reservation.getStartTime(),
                        reservation.getEndTime()))
                .toList();
    }

    public List<SmallRoomReservationResponse> getStudentReservations(Integer studentId) {
        deleteExpiredReservations();
        return reservationRepository.findByStudentIdOrderByStartTimeDesc(studentId)
                .stream()
                .map(reservation -> new SmallRoomReservationResponse(
                        reservation.getId(),
                        reservation.getStudentId(),
                        reservation.getBuildingName(),
                        reservation.getRoomName(),
                        reservation.getStartTime(),
                        reservation.getEndTime()
                ))
                .toList();
    }

    // 특정 건물의 현재 사용중인 강의실 조회
    public SmallRoomUsageResponse getBuildingRoomStatus(String buildingName) {
        deleteExpiredReservations();
        LocalDateTime now = LocalDateTime.now();

        List<RoomStatus> roomStatuses = reservationRepository
                .findByBuildingNameAndStartTimeBeforeAndEndTimeAfter(buildingName, now, now)
                .stream()
                .map(reservation -> new RoomStatus(
                        reservation.getRoomName(),
                        true,
                        reservation.getStudentId()
                ))
                .toList();

        return new SmallRoomUsageResponse(buildingName, roomStatuses);
    }
}