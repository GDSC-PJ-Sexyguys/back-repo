package com.example.GDSC_hackathon.service;

import com.example.GDSC_hackathon.domain.LargeLectureRoomTicketHistory;
import com.example.GDSC_hackathon.exception.GDSCException;
import com.example.GDSC_hackathon.records.LargeRoomTicketRequest;
import com.example.GDSC_hackathon.records.LargeRoomTicketResponse;
import com.example.GDSC_hackathon.repository.LargeRoomTicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LargeRoomTicketService {

    private final LargeRoomTicketRepository largeRoomTicketRepository;

    @Transactional
    public Long issueTicket(LargeRoomTicketRequest request) {
        deleteTicketIfExpired();
        if (largeRoomTicketRepository.existsByStudentId(request.bookerStudentId())) {
            throw GDSCException.ALREADY_IN_USE_OTHER_SEAT;
        }

        if (largeRoomTicketRepository.existsBySeatNumberAndBuildingNameAndRoomName(
                request.seatNumber(),
                request.buildingName(),
                request.roomName())) {
            throw GDSCException.SEAT_ALREADY_OCCUPIED;
        }

        LargeLectureRoomTicketHistory ticket = LargeLectureRoomTicketHistory.builder()
                .studentId(request.bookerStudentId())
                .buildingName(request.buildingName())
                .roomName(request.roomName())
                .seatNumber(request.seatNumber())
                .build();

        LargeLectureRoomTicketHistory savedTicket = largeRoomTicketRepository.save(ticket);
        return savedTicket.getId();
    }

    private void deleteTicketIfExpired(){
        largeRoomTicketRepository.deleteAllByEndTimeBefore(LocalDateTime.now());
    }

    public LargeRoomTicketResponse getTicketByStudentId(Integer studentId) {
        deleteTicketIfExpired();
        LargeLectureRoomTicketHistory ticket = largeRoomTicketRepository.findByStudentId(studentId)
                .orElseThrow(() -> GDSCException.TICKET_NOT_FOUND);

        return new LargeRoomTicketResponse(
                ticket.getId(),
                ticket.getStudentId(),
                ticket.getBuildingName(),
                ticket.getRoomName(),
                ticket.getSeatNumber(),
                ticket.getStartTime(),
                ticket.getEndTime()
        );
    }

    // LargeRoomTicketService.java에 추가
    public List<Integer> getOccupiedSeats(String buildingName, String roomName) {
        deleteTicketIfExpired();  // 만료된 티켓 먼저 삭제
        return largeRoomTicketRepository
                .findByBuildingNameAndRoomName(buildingName, roomName)
                .stream()
                .map(LargeLectureRoomTicketHistory::getSeatNumber)
                .toList();
    }

}