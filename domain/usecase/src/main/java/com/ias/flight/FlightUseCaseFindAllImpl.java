package com.ias.flight;

import com.ias.FlightDomain;
import com.ias.gateway.flight.FlightRepositoryFindAllGateway;

import java.util.List;
import java.util.logging.Logger;

public class FlightUseCaseFindAllImpl {

    private static final Logger LOGGER = Logger.getLogger(FlightUseCaseFindAllImpl.class.getName());
    private final FlightRepositoryFindAllGateway flightRepositoryFindAllGateway;

    public FlightUseCaseFindAllImpl(FlightRepositoryFindAllGateway flightRepositoryFindAllGateway) {
        this.flightRepositoryFindAllGateway = flightRepositoryFindAllGateway;
    }

    public List<FlightDomain> getAllFlights(){
        LOGGER.info("Get all flights");
        return flightRepositoryFindAllGateway.findAll();
    }
}
