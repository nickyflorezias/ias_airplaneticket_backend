package com.ias;

import com.ias.dto.ResponseDTO;
import com.ias.dto.request.FlightDTO;
import com.ias.flight.FlightUseCaseFindAllImpl;
import com.ias.flight.FlightUseCaseFindByIdImpl;
import com.ias.flight.FlightUseCaseSaveImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightUseCaseFindAllImpl flightUseCaseFindAll;
    private final FlightUseCaseFindByIdImpl flightUseCaseFindById;
    private final FlightUseCaseSaveImpl flightUseCaseSave;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllFlights(){
        List<FlightDomain> domainResponse = flightUseCaseFindAll.getAllFlights();
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
        FlightDomain flight = flightUseCaseFindById.getFlightById(flightId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        FlightDTO.fromDomain(flight),
                        HttpStatus.OK,
                        "Get Flight by id."
                ));
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> createFlight(@Valid @RequestBody FlightDTO flightDomain){
        FlightDomain domainResponse = flightUseCaseSave.createFlight(flightDomain.toDomain());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(
                        FlightDTO.fromDomain(domainResponse),
                        HttpStatus.CREATED,
                        "Flight created successfully."
                ));
    }
}
