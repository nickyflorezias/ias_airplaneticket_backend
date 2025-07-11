package com.ias;

import com.ias.gateway.flight.FlightRepositoryFindGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class FlightUseCase {

    private final FlightService flightService;

    private final FlightRepositoryFindGateway flightRepositoryFindGateway;

    private static final Logger logger = Logger.getLogger(FlightUseCase.class.getName());

    public FlightUseCase(FlightService flightService,
                         FlightRepositoryFindGateway flightRepositoryFindGateway) {
        this.flightService = flightService;
        this.flightRepositoryFindGateway = flightRepositoryFindGateway;
    }

    public List<FlightDomain> getAllFlights(){
        logger.info("Get all flights");
        return flightRepositoryFindGateway.findAll();
    }

    public FlightDomain getFlightById(Long flightId){
        return flightService.getFlightById(flightId);
    }

    public FlightDomain createFlight(FlightDomain flightDomain){
        validNameIsOnlyText(flightDomain.getName());
        validNameIsLongerThanThirty(flightDomain.getName());
        validDateIsBefore(flightDomain.getDate());
        validOriginCityEqualsDestiny(flightDomain.getOriginCity(), flightDomain.getDestinyCity());

        logger.fine("Creating flight with = " + flightDomain);
        return flightService.saveFlight(flightDomain);
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
