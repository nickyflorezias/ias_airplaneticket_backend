package com.ias;

import com.ias.dto.ResponseDTO;
import com.ias.dto.request.FlightDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightUseCase flightUseCase;

    public FlightController(FlightUseCase flightUseCase) {
        this.flightUseCase = flightUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllFlights(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        flightUseCase.getAllFlights(),
                        HttpStatus.OK,
                        "All Flights."
                ));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<ResponseDTO> getFlightById(@PathVariable Long flightId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        flightUseCase.getFlightById(flightId),
                        HttpStatus.OK,
                        "Get Flight by id."
                ));
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> createFlight(@RequestBody FlightDTO flightDomain){
        FlightDomain flight = new FlightDomain(
                null,
                flightDomain.getName(),
                flightDomain.getOriginCity(),
                flightDomain.getDestinyCity(),
                flightDomain.getDate(),
                flightDomain.getPlaneName(),
                flightDomain.getCantSeats(),
                false,
                null
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        flightUseCase.createFlight(flight),
                        HttpStatus.CREATED,
                        "Flight created successfully."
                ));
    }
}
