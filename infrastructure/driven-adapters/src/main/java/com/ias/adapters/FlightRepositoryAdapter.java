package com.ias.adapters;

import com.ias.FlightDomain;
import com.ias.gateway.FlightRepositoryGateway;
import com.ias.repositories.FlightRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepositoryAdapter implements FlightRepositoryGateway {

    private final FlightRepository flightRepository;

    public FlightRepositoryAdapter(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightDomain> findAll() {
        return List.of();
    }

    @Override
    public FlightDomain findById(Long flightId) {
        return null;
    }

    @Override
    public FlightDomain save(FlightDomain flightDomain) {
        return null;
    }
}
