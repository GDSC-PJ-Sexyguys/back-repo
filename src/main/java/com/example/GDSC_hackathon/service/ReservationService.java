package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.Classroom;
import com.example.GDSC_hackathon.domain.Reservation;
import com.example.GDSC_hackathon.domain.Seat;
import com.example.GDSC_hackathon.enumClass.ClassroomType;
import com.example.GDSC_hackathon.repository.ClassroomRepo;
import com.example.GDSC_hackathon.repository.ReservationRepo;
import com.example.GDSC_hackathon.repository.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private SeatService seatService;


    //예약 요청시 저장하는 함수
    public Reservation saveReservation(Reservation reservation, LocalDateTime startTime){
        //중복확인
        reservation.setReservationTime(LocalDateTime.now());
        Classroom resevedClassroom = reservation.getClassroom();
        //대강의실일 경우 현재시간을 기준으로 startTime, endTime 설정
        if(resevedClassroom.getType() == ClassroomType.LARGE){
            reservation.setStartTime(LocalDateTime.now());
            reservation.setEndTime(reservation.getStartTime().plusHours(6));
        }
        //소강의실일 경우 reservation에 담겨있는 정보 그대로 저장

        //예약됨
        return reservationRepo.save(reservation);
    }

    //현재 시간과 넘겨받은 classroom을 반영하여 현재시간 기준 예약 상황 확인
    //예약 상황을 Seat의 List로 출력하여 개수 및 좌석 정보를 알 수 있도록 함
    public List<Seat> getActiveSeats(Long classroomId) {
        LocalDateTime now = LocalDateTime.now();
        return reservationRepo.findActiveReservationsByClassroomAndCurrentTime(classroomId, now).stream()
                .map(reservation -> reservation.getSeat()) // Reservation에서 Seat 추출
                .distinct() // 중복 제거
                .collect(Collectors.toList());
    }
    public Optional<Reservation> getReservationById(Long id){
        return reservationRepo.findById(id);
    }

    // 현재시간 기준 만료된 예약 삭제
    @Transactional
    public void deletePastReservations() {
        LocalDateTime now = LocalDateTime.now();
        reservationRepo.deleteReservationsPastEndTime(now);
    }
    public Optional<Reservation> getReservationsByStudentId(Long studentId){
        return reservationRepo.findByStudentId(studentId);
    }

    // 좌석 별 예약 현황 출력 함수(대강의실 용)
    public List<Reservation> getReservationsBySeatId(Long seatId){
        LocalDateTime now = LocalDateTime.now();
        return reservationRepo.findReservationsBySeatAndCurrentTime(seatId, now);
    }

    // 강의실 별 예약 현황 출력 함수(소강의실 용)
    public List<Reservation> getReservationsByClassroomId(Long classroomId) {
        LocalDateTime now = LocalDateTime.now();
        return reservationRepo.findReservationsByClassroomAndCurrentTime(classroomId, now);
    }


}
