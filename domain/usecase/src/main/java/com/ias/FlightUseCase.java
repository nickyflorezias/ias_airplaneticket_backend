package com.ias;

import com.ias.gateway.FlightRepositoryGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class FlightUseCase {

    private final FlightRepositoryGateway flightRepositoryGateway;

    private static final Logger logger = Logger.getLogger(FlightUseCase.class.getName());

    public FlightUseCase(FlightRepositoryGateway flightRepositoryGateway) {
        this.flightRepositoryGateway = flightRepositoryGateway;
    }

    public List<FlightDomain> getAllFlights(){
        logger.info("Get all flights");
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

        logger.fine("Creating flight with = " + flightDomain.toString());
        return flightRepositoryGateway.save(flightDomain);
    }

    private void isValid(FlightDomain flightDomain){
        if(!flightDomain.getName().matches("^[A-Za-z]+$")){
            logger.severe("Flight name is not valid, pattern invalid = " + flightDomain.getName());
            throw new UserIllegalArgumentException("Flight name must be only text.");
        }

        if(flightDomain.getName().length() > 30){
            logger.severe("Flight name is not valid, can't be longer than 30 characters =" + flightDomain.getName());
            throw new UserIllegalArgumentException("Flight name can't be longer than 30 characters");
        }

        if(flightDomain.getOriginCity().equals(flightDomain.getDestinyCity())){
            logger.severe("Flight OriginCity can't be equal to DestinationCity." + flightDomain.getOriginCity() + " " + flightDomain.getDestinyCity());
            throw new FlightIllegalArgumentException("OriginCity can't be equal to DestinationCity.");
        }

        if(flightDomain.getDate().isBefore(LocalDateTime.now())){
            logger.severe("Flight date cant be before now" + flightDomain.getDate());
            throw new FlightIllegalArgumentException("Date can't be before now.");
        }
    }
}
