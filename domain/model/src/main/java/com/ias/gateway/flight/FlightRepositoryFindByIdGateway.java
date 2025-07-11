package com.ias.gateway.flight;

import com.ias.FlightDomain;

public interface FlightRepositoryFindByIdGateway {
    FlightDomain findById(Long flightId);
}
