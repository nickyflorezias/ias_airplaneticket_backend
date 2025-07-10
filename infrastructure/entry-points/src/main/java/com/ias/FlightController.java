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
        List<FlightDomain> domainResponse = flightUseCase.getAllFlights();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        domainResponse.stream()
                                .map(FlightDTO::fromDomain).toList(),
                        HttpStatus.OK,
                        "All Flights."
                ));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<ResponseDTO> getFlightById(@PathVariable Long flightId){
        FlightDomain flight = flightUseCase.getFlightById(flightId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        FlightDTO.fromDomain(flight),
                        HttpStatus.OK,
                        "Get Flight by id."
                ));
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> createFlight(@RequestBody FlightDTO flightDomain){
        FlightDomain domainResponse = flightUseCase.createFlight(flightDomain.toDomain());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        FlightDTO.fromDomain(domainResponse),
                        HttpStatus.CREATED,
                        "Flight created successfully."
                ));
    }
}
