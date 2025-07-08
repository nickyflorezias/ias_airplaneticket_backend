package com.ias;

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
    public ResponseEntity<List<FlightDomain>> getAllFlights(){
        return ResponseEntity.status(HttpStatus.OK).body(flightUseCase.getAllFlights());
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDomain> getFlightById(@PathVariable Long flightId){
        return ResponseEntity.status(HttpStatus.OK).body(flightUseCase.getFlightById(flightId));
    }
    @PostMapping
    public ResponseEntity<FlightDomain> createFlight(@RequestBody FlightDomain flightDomain){
        return ResponseEntity.status(HttpStatus.OK).body(flightUseCase.createFlight(flightDomain));
    }
}
