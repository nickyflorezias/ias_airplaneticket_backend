package com.ias.gateway;

import com.ias.FlightDomain;

import java.util.List;

public interface FlightRepositoryGateway {
    List<FlightDomain> findAll();
    FlightDomain findById(Long flightId);
    FlightDomain save(FlightDomain flightDomain);
}
