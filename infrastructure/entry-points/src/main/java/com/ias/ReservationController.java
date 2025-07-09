package com.ias;

import com.ias.dto.request.ReservationDTO;
import com.ias.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final static Logger logger = Logger.getLogger(ReservationController.class.getName());

    private final ReservationUseCase reservationUseCase;

    public ReservationController(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO> getReservationsByUserId(@PathVariable Long userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        reservationUseCase.getAllReservationsByUserId(userId),
                        HttpStatus.OK,
                        "Get all reservations with user id " + userId
                ));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseDTO> getReservationsById(@PathVariable Long reservationId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        reservationUseCase.getById(reservationId),
                        HttpStatus.OK,
                        "Get all reservations with id " + reservationId
                ));
    }

    @PostMapping("/{userId}/{ticketId}")
    public ResponseEntity<ResponseDTO> createReservation(@PathVariable Long userId, @RequestBody ReservationDTO reservation, @PathVariable Long ticketId){
        logger.info(reservation.toString());
        ReservationDomain reservationDomain = new ReservationDomain(
                null,
                reservation.getDate(),
                true,
                reservation.getDescription(),
                null,
                null
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        reservationUseCase.createReservation(userId, reservationDomain, ticketId),
                        HttpStatus.CREATED,
                        "Reservation created to Date " + reservationDomain.getDate()
                ));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ResponseDTO> cancelReservation(@PathVariable Long reservationId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        reservationUseCase.cancelReservation(reservationId),
                        HttpStatus.OK,
                        "Reservation canceled successfully."
                ));
    }
}
