package com.ias.gateway.flight;

import com.ias.FlightDomain;

public interface FlightRepositorySaveGateway {
    FlightDomain save(FlightDomain flightDomain);
}
