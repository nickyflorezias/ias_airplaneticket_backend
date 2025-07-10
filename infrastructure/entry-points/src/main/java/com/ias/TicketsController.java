package com.ias;

import com.ias.dto.ResponseDTO;
import com.ias.dto.request.TicketDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketUseCase ticketUseCase;

    public TicketsController(TicketUseCase ticketUseCase) {
        this.ticketUseCase = ticketUseCase;
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ResponseDTO> getAllTicketsByReservationId(@PathVariable Long reservationId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ticketUseCase.getAllTicketsByReservationId(reservationId),
                        HttpStatus.OK,
                        "Get tickets with reservation id " + reservationId
                ));
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<ResponseDTO> getAllTicketsByFlightId(@PathVariable Long flightId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ticketUseCase.getAllTicketsByFlightId(flightId),
                        HttpStatus.OK,
                        "Get tickets with flight id " + flightId
                ));
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<ResponseDTO> createTicket(@PathVariable Long flightId, @RequestBody @Valid TicketDTO ticketDomain){
        TicketDomain ticket = new TicketDomain(
                null,
                ticketDomain.getSeat(),
                null,
                null,
                null
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        ticketUseCase.createTicket(flightId, ticket),
                        HttpStatus.CREATED,
                        "Created ticket successfully."
                ));
    }
}
