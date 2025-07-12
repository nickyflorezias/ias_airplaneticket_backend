package com.ias;

import com.ias.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@Hidden
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FlightFullException.class)
    public ResponseEntity<ResponseDTO> flightFullExceptionHandler(FlightFullException flightFullException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.CONFLICT,
                        flightFullException.getMessage()
                ));
    }

    @ExceptionHandler(FlightIllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> flightIllegalArgumentExceptionHandler(FlightIllegalArgumentException flightIllegalArgumentException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.BAD_REQUEST,
                        flightIllegalArgumentException.getMessage()
                ));
    }

    @ExceptionHandler(NonFlightAvailableException.class)
    public ResponseEntity<ResponseDTO> nonFlightAvailableExceptionHandler(NonFlightAvailableException nonFlightAvailableException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.CONFLICT,
                        nonFlightAvailableException.getMessage()
                ));
    }

    @ExceptionHandler(ReservationAlreadyCanceledException.class)
    public ResponseEntity<ResponseDTO> reservationAlreadyCanceledExceptionHandler(ReservationAlreadyCanceledException reservationAlreadyCanceledException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.CONFLICT,
                        reservationAlreadyCanceledException.getMessage()
                ));
    }

    @ExceptionHandler(UserIllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> userIllegalArgumentExceptionHandler(UserIllegalArgumentException userIllegalArgumentException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.BAD_REQUEST,
                        userIllegalArgumentException.getMessage()
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.BAD_REQUEST,
                        illegalArgumentException.getMessage()
                ));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseDTO> sQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(
                        null,
                        HttpStatus.CONFLICT,
                        sqlIntegrityConstraintViolationException.getMessage().split("'")[0].trim()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseDTO(
                                null,
                                HttpStatus.BAD_REQUEST,
                                methodArgumentNotValidException.getBindingResult()
                                        .getFieldErrors()
                                        .stream()
                                        .map(FieldError::getDefaultMessage)
                                        .findFirst()
                                        .orElse("Validation error")
                        )
                );
    }

}
