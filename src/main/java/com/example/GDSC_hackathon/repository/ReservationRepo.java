package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Override
    Optional<Reservation> findById(Long aLong);
    Optional<Reservation> findByStudentId(Long studentId);
    @Override
    Reservation save(Reservation reservation);
    @Override
    void deleteById(Long id);

}
