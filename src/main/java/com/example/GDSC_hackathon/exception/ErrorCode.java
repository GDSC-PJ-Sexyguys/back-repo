package com.example.GDSC_hackathon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST - 잘못된 요청에 대한 에러들 */
    ALREADY_IN_USE_OTHER_SEAT(HttpStatus.BAD_REQUEST, "ALREADY_IN_USE_OTHER_SEAT", "이미 다른 좌석을 사용중인 학생입니다."),
    SEAT_ALREADY_OCCUPIED(HttpStatus.BAD_REQUEST, "SEAT_ALREADY_OCCUPIED", "다른 사용자가 사용중인 좌석입니다."),
    INVALID_SEAT_NUMBER(HttpStatus.BAD_REQUEST, "INVALID_SEAT_NUMBER", "유효하지 않은 좌석 번호입니다."),
    DUPLICATE_TICKET_REQUEST(HttpStatus.BAD_REQUEST, "DUPLICATE_TICKET_REQUEST", "이미 발권 요청된 좌석입니다."),
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND,"TICKET_NOT_FOUND","발권 이력을 찾을 수 없습니다."),

    ROOM_ALREADY_RESERVED(HttpStatus.BAD_REQUEST, "ROOM_ALREADY_RESERVED","해당 시간에 이미 예약된 강의실입니다."),
    ALREADY_HAS_RESERVATION(HttpStatus.BAD_REQUEST, "ALREADY_HAS_RESERVATION","해당 시간에 이미 다른 예약이 있습니다."),
    INVALID_START_TIME(HttpStatus.BAD_REQUEST, "INVALID_START_TIME","시작 시간이 현재 시간보다 이전입니다."),
    INVALID_END_TIME(HttpStatus.BAD_REQUEST, "INVALID_END_TIME","종료 시간이 시작 시간보다 이전입니다."),
    EXCEEDED_MAX_RESERVATION_TIME(HttpStatus.BAD_REQUEST, "EXCEEDED_MAX_RESERVATION_TIME","최대 예약 가능 시간을 초과했습니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "RESERVATION_NOT_FOUND","예약 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
