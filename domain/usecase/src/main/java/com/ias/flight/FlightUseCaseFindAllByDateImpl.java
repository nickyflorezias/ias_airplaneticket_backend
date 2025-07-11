package com.ias.flight;

import com.ias.FlightDomain;
import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class FlightUseCaseFindAllByDateImpl {

    private final Logger LOGGER = Logger.getLogger(FlightUseCaseFindAllByDateImpl.class.getName());
    private final FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway;

    public FlightUseCaseFindAllByDateImpl(FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway) {
        this.flightRepositoryFindAllByDateGateway = flightRepositoryFindAllByDateGateway;
    }

    public List<FlightDomain> getAllFlightsByDate(LocalDateTime date){
        LOGGER.info("Get all flights");
        return flightRepositoryFindAllByDateGateway.findAllByDate(date);
    }
}
