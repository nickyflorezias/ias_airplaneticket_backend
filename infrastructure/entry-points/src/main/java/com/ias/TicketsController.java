package com.ias;

import com.ias.dto.ResponseDTO;
import com.ias.dto.request.TicketDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<ResponseDTO> getAllTicketsByFlightId(@PathVariable Long flightId){
        List<TicketDomain> ticketResponse = ticketUseCase.getAllTicketsByFlightId(flightId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        ticketResponse.stream()
                                .map(TicketDTO::fromDomain).toList(),
                        HttpStatus.OK,
                        "Get tickets with flight id " + flightId
                ));
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<ResponseDTO> createTicket(@PathVariable Long flightId, @RequestBody @Valid TicketDTO ticketDomain){
        TicketDomain ticketResponse = ticketUseCase.createTicket(flightId, ticketDomain.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        TicketDTO.fromDomain(ticketResponse),
                        HttpStatus.CREATED,
                        "Created ticket successfully."
                ));
    }
}
