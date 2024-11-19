package com.example.GDSC_hackathon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class GDSCException extends RuntimeException {

    public static final GDSCException ALREADY_IN_USE_OTHER_SEAT = new GDSCException(ErrorCode.ALREADY_IN_USE_OTHER_SEAT);
    public static final GDSCException SEAT_ALREADY_OCCUPIED = new GDSCException(ErrorCode.SEAT_ALREADY_OCCUPIED);
    public static final GDSCException INVALID_SEAT_NUMBER = new GDSCException(ErrorCode.INVALID_SEAT_NUMBER);
    public static final GDSCException DUPLICATE_TICKET_REQUEST = new GDSCException(ErrorCode.DUPLICATE_TICKET_REQUEST);
    public static final GDSCException TICKET_NOT_FOUND = new GDSCException(ErrorCode.TICKET_NOT_FOUND);


    public static final GDSCException ROOM_ALREADY_RESERVED = new GDSCException(ErrorCode.ROOM_ALREADY_RESERVED);
    public static final GDSCException ALREADY_HAS_RESERVATION = new GDSCException(ErrorCode.ALREADY_HAS_RESERVATION);
    public static final GDSCException INVALID_START_TIME = new GDSCException(ErrorCode.INVALID_START_TIME);
    public static final GDSCException INVALID_END_TIME = new GDSCException(ErrorCode.INVALID_END_TIME);
    public static final GDSCException EXCEEDED_MAX_RESERVATION_TIME = new GDSCException(ErrorCode.EXCEEDED_MAX_RESERVATION_TIME);
    public static final GDSCException RESERVATION_NOT_FOUND = new GDSCException(ErrorCode.RESERVATION_NOT_FOUND);

    private final ErrorCode errorCode;

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}