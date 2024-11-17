package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.Seat;
import com.example.GDSC_hackathon.repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;


    // Create or Update a Seat
    public Seat saveSeat(Seat seat) {
        return seatRepo.save(seat);
    }

    // Get Seat by ID
    public Optional<Seat> getSeatById(Long id) {
        return seatRepo.findById(id);
    }

    // Get All Seats
    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

}
