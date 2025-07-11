package com.ias.gateway.flight;

import com.ias.FlightDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepositoryFindGateway {
    List<FlightDomain> findAll();
    FlightDomain findById(Long flightId);
    List<FlightDomain> findAllByDate(LocalDateTime date);
}
