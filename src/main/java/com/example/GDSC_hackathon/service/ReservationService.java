package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.Classroom;
import com.example.GDSC_hackathon.domain.Reservation;
import com.example.GDSC_hackathon.enumClass.ClassroomType;
import com.example.GDSC_hackathon.repository.ClassroomRepo;
import com.example.GDSC_hackathon.repository.ReservationRepo;
import com.example.GDSC_hackathon.repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private SeatService seatService;


    public Reservation saveReservation(Reservation reservation, LocalDateTime startTime){
        //중복확인
        reservation.setReservationTime(LocalDateTime.now());
        Classroom resevedClassroom = reservation.getClassroom();
        //대강의실(타입확인 필)
        if(resevedClassroom.getType() == ClassroomType.LARGE){
            reservation.setStartTime(LocalDateTime.now());
        }
        //소강의실

        reservation.setEndTime(reservation.getStartTime().plusHours(6));
        //classroom update
        classroomService.updateClassroom(resevedClassroom, 1);
        //seatupdate
        seatService.updateSeat(reservation.getSeat(), true);
        //예약됨
        return reservationRepo.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id){
        return reservationRepo.findById(id);
    }

    public Optional<Reservation> getReservationByStudentId(Long studentId){
        return reservationRepo.findByStudentId(studentId);
    }

}
