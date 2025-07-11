package com.ias.flight;

import com.ias.FlightDomain;
import com.ias.gateway.flight.FlightRepositoryFindByIdGateway;

public class FlightUseCaseFindByIdImpl {

    private final FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway;

    public FlightUseCaseFindByIdImpl(FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway) {
        this.flightRepositoryFindByIdGateway = flightRepositoryFindByIdGateway;
    }

    public FlightDomain getFlightById(Long flightId){
        return flightRepositoryFindByIdGateway.findById(flightId);
    }
}
