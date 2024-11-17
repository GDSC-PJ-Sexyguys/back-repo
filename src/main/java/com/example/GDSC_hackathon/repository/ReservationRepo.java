package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Override
    Optional<Reservation> findById(Long aLong);
    Optional<Reservation> findByStudentId(Long studentId);
    @Override
    Reservation save(Reservation reservation);
    @Override
    void deleteById(Long id);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.classroom.id = :classroomId " +
            "AND :currentTime BETWEEN r.startTime AND r.endTime")
    List<Reservation> findActiveReservationsByClassroomAndCurrentTime(
            @Param("classroomId") Long classroomId,
            @Param("currentTime") LocalDateTime currentTime
    );

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.seat.id = :seatId " +
            "AND :currentTime < r.endTime")
    List<Reservation> findReservationsBySeatAndCurrentTime(
            @Param("seatId") Long seatId,
            @Param("currentTime") LocalDateTime currentTime
    );
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.classroom.id = :classroomId " +
            "AND :currentTime < r.endTime")
    List<Reservation> findReservationsByClassroomAndCurrentTime(
            @Param("classroomId") Long classroomId,
            @Param("currentTime") LocalDateTime currentTime
    );
    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.endTime < :currentTime")
    void deleteReservationsPastEndTime(LocalDateTime currentTime);
}
