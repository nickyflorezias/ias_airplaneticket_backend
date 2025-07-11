package com.ias.gateway.flight;

import com.ias.FlightDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepositoryFindAllByDateGateway {
    List<FlightDomain> findAllByDate(LocalDateTime date);
}
