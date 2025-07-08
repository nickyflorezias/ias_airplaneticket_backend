package com.ias;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketUseCase ticketUseCase;

    public TicketsController(TicketUseCase ticketUseCase) {
        this.ticketUseCase = ticketUseCase;
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<List<TicketDomain>> getAllTicketsByReservationId(@PathVariable Long reservationId){
        return ResponseEntity.status(HttpStatus.OK).body(ticketUseCase.getAllTicketsByReservationId(reservationId));
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<List<TicketDomain>> getAllTicketsByFlightId(@PathVariable Long flightId){
        return ResponseEntity.status(HttpStatus.OK).body(ticketUseCase.getAllTicketsByFlightId(flightId));
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<TicketDomain> createTicket(@PathVariable Long flightId, @RequestBody TicketDomain ticketDomain){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketUseCase.createTicket(flightId, ticketDomain));
    }
}
