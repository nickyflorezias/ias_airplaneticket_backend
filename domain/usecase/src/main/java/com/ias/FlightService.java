package com.ias;

import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;
import com.ias.gateway.flight.FlightRepositoryFindByIdGateway;
import com.ias.gateway.flight.FlightRepositorySaveGateway;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class FlightService {

    private static final Logger LOGGER = Logger.getLogger(FlightService.class.getName());

    private final FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway;
    private final FlightRepositorySaveGateway flightRepositorySaveGateway;
    private final FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway;

    public FlightService(FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway, FlightRepositorySaveGateway flightRepositorySaveGateway, FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway) {
        this.flightRepositoryFindByIdGateway = flightRepositoryFindByIdGateway;
        this.flightRepositorySaveGateway = flightRepositorySaveGateway;
        this.flightRepositoryFindAllByDateGateway = flightRepositoryFindAllByDateGateway;
    }

    public FlightDomain getFlightById(Long flightId){
        return flightRepositoryFindByIdGateway.findById(flightId);
    }

    public FlightDomain saveFlight(FlightDomain flightDomain){
        return flightRepositorySaveGateway.save(flightDomain);
    }

    public void validNameIsOnlyText(String name){
        if(!name.matches("^[A-Za-z]+$")){
            LOGGER.severe("Flight name is not valid, pattern invalid = " + name);
            throw new FlightIllegalArgumentException("Flight name must be only text.");
        }
    }

    public void validNameIsLongerThanThirty(String name){
        if(!name.matches("^.{1,30}$")){
            LOGGER.severe("Flight name is not valid, can't be longer than 30 characters =" + name);
            throw new FlightIllegalArgumentException("Flight name can't be longer than 30 characters");
        }
    }

    public void validDateIsBefore(LocalDateTime date){
        if(date.isBefore(LocalDateTime.now())){
            LOGGER.severe("Flight date cant be before now " + date);
            throw new FlightIllegalArgumentException("Flight date can't be before now.");
        }
    }

    public void validOriginCityEqualsDestiny(String originCity, String destinyCity){
        if(originCity.equals(destinyCity)){
            LOGGER.severe("Flight OriginCity can't be equal to DestinationCity." + originCity + " " + destinyCity);
            throw new FlightIllegalArgumentException("OriginCity can't be equal to DestinationCity.");
        }
    }
}
