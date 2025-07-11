package com.ias.flight;

import com.ias.FlightDomain;
import com.ias.FlightService;
import com.ias.gateway.flight.FlightRepositorySaveGateway;

import java.util.logging.Logger;

public class FlightUseCaseSaveImpl {

    private static final Logger LOGGER = Logger.getLogger(FlightUseCaseSaveImpl.class.getName());
    private final FlightRepositorySaveGateway flightRepositorySaveGateway;
    private final FlightService flightService;

    public FlightUseCaseSaveImpl(FlightRepositorySaveGateway flightRepositorySaveGateway, FlightService flightService) {
        this.flightRepositorySaveGateway = flightRepositorySaveGateway;
        this.flightService = flightService;
    }

    public FlightDomain createFlight(FlightDomain flightDomain){
        flightService.validNameIsOnlyText(flightDomain.getName());
        flightService.validNameIsLongerThanThirty(flightDomain.getName());
        flightService.validDateIsBefore(flightDomain.getDate());
        flightService.validOriginCityEqualsDestiny(flightDomain.getOriginCity(), flightDomain.getDestinyCity());

        LOGGER.fine("Creating flight with = " + flightDomain);
        return flightRepositorySaveGateway.save(flightDomain);
    }
}
