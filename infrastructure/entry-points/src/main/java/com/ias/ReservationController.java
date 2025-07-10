package com.ias;

import com.ias.dto.request.ReservationDTO;
import com.ias.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationUseCase reservationUseCase;

    public ReservationController(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getReservationsByUserId(@PathVariable Long userId){
        List<ReservationDomain> reservationsResponse = reservationUseCase.getAllReservationsByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        reservationsResponse.stream()
                                .map(ReservationDTO::fromDomain).toList(),
                        HttpStatus.OK,
                        "Get all reservations with user id " + userId
                ));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseDTO> getReservationById(@PathVariable Long reservationId){
        ReservationDomain reservationResponse = reservationUseCase.getById(reservationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ReservationDTO.fromDomain(reservationResponse),
                        HttpStatus.OK,
                        "Get all reservations with id " + reservationId
                ));
    }

    @PostMapping("/{userId}/{ticketId}")
    public ResponseEntity<ResponseDTO> createReservation(@PathVariable Long userId, @RequestBody ReservationDTO reservation, @PathVariable Long ticketId){
        ReservationDomain reservationResponse = reservationUseCase.createReservation(userId, reservation.toDomain(), ticketId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        ReservationDTO.fromDomain(reservationResponse),
                        HttpStatus.CREATED,
                        "Reservation created to Date " + reservationResponse.getDate()
                ));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ResponseDTO> cancelReservation(@PathVariable Long reservationId){
        ReservationDomain reservationResponse = reservationUseCase.cancelReservation(reservationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ReservationDTO.fromDomain(reservationResponse),
                        HttpStatus.OK,
                        "Reservation canceled successfully."
                ));
    }

    @PutMapping("/date/{reservationId}")
    public ResponseEntity<ResponseDTO> updateDateReservation(@PathVariable Long reservationId, @RequestBody ReservationDTO reservation){
        ReservationDomain reservationResponse = reservationUseCase.updateDate(reservationId, reservation.getDate());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ReservationDTO.fromDomain(reservationResponse),
                        HttpStatus.OK,
                        "Reservation date updated successfully to " + reservation.getDate()
                ));
    }
}
