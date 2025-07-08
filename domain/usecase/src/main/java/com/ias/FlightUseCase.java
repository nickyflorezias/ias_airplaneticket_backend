package com.ias;

import com.ias.gateway.FlightRepositoryGateway;

import java.time.LocalDateTime;
import java.util.List;

public class FlightUseCase {

    private final FlightRepositoryGateway flightRepositoryGateway;

    public FlightUseCase(FlightRepositoryGateway flightRepositoryGateway) {
        this.flightRepositoryGateway = flightRepositoryGateway;
    }

    public List<FlightDomain> getAllFlights(){
        return flightRepositoryGateway.findAll();
    }

    public FlightDomain getFlightById(Long flightId){
        return flightRepositoryGateway.findById(flightId);
    }

    public FlightDomain createFlight(FlightDomain flightDomain){
        if(flightDomain.getName() == null || flightDomain.getDate() == null || flightDomain.getOriginCity() == null || flightDomain.getDestinyCity() == null){
            throw new IllegalArgumentException("Invalid: all arguments must not be null.");
        }

        isValid(flightDomain);

        return flightRepositoryGateway.save(flightDomain);
    }

    private void isValid(FlightDomain flightDomain){
        if(!flightDomain.getName().matches("^[A-Za-z]+$")){
            throw new UserIllegalArgumentException("Flight name must be only text.");
        }

        if(flightDomain.getName().length() > 30){
            throw new UserIllegalArgumentException("Flight name can't be longer than 30 characters");
        }

        if(flightDomain.getOriginCity().equals(flightDomain.getDestinyCity())){
            throw new FlightIllegalArgumentException("OriginCity can't be equal to DestinationCity.");
        }

        if(flightDomain.getDate().isBefore(LocalDateTime.now())){
            throw new FlightIllegalArgumentException("Date can't be before now.");
        }
    }
}
