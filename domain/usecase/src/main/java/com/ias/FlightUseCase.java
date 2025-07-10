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

        validNameIsOnlyText(flightDomain.getName());
        validNameIsLongerThanThirty(flightDomain.getName());
        validDateIsBefore(flightDomain.getDate());
        validOriginCityEqualsDestiny(flightDomain.getOriginCity(), flightDomain.getDestinyCity());

        logger.fine("Creating flight with = " + flightDomain);
        return flightRepositoryGateway.save(flightDomain);
    }

    private void validNameIsOnlyText(String name){
        if(!name.matches("^[A-Za-z]+$")){
            logger.severe("Flight name is not valid, pattern invalid = " + name);
            throw new FlightIllegalArgumentException("Flight name must be only text.");
        }
    }

    public void validNameIsLongerThanThirty(String name){
        if(!name.matches("^.{1,30}$")){
            logger.severe("Flight name is not valid, can't be longer than 30 characters =" + name);
            throw new FlightIllegalArgumentException("Flight name can't be longer than 30 characters");
        }
    }

    public void validDateIsBefore(LocalDateTime date){
        if(date.isBefore(LocalDateTime.now())){
            logger.severe("Flight date cant be before now " + date);
            throw new FlightIllegalArgumentException("Flight date can't be before now.");
        }
    }

    public void validOriginCityEqualsDestiny(String originCity, String destinyCity){
        if(originCity.equals(destinyCity)){
            logger.severe("Flight OriginCity can't be equal to DestinationCity." + originCity + " " + destinyCity);
            throw new FlightIllegalArgumentException("OriginCity can't be equal to DestinationCity.");
        }
    }
}
