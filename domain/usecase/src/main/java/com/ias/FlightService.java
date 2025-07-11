package com.ias;

import com.ias.gateway.flight.FlightRepositoryFindGateway;
import com.ias.gateway.flight.FlightRepositorySaveGateway;

public class FlightService {

    private final FlightRepositoryFindGateway flightRepositoryFindGateway;
    private final FlightRepositorySaveGateway flightRepositorySaveGateway;

    public FlightService(FlightRepositoryFindGateway flightRepositoryFindGateway, FlightRepositorySaveGateway flightRepositorySaveGateway) {
        this.flightRepositoryFindGateway = flightRepositoryFindGateway;
        this.flightRepositorySaveGateway = flightRepositorySaveGateway;
    }

    public FlightDomain getFlightById(Long flightId){
        return flightRepositoryFindGateway.findById(flightId);
    }

    public FlightDomain saveFlight(FlightDomain flightDomain){
        return flightRepositorySaveGateway.save(flightDomain);
    }
}
