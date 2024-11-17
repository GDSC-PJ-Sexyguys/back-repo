package com.example.GDSC_hackathon.repository;

import com.example.GDSC_hackathon.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepo extends JpaRepository<Seat, Long> {
    @Override
    Optional<Seat> findById(Long aLong);
    @Override
    Seat save(Seat seat);
}
