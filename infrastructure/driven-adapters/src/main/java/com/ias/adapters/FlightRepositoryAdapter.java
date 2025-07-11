package com.ias.adapters;

import com.ias.FlightDomain;
import com.ias.dbo.FlightDBO;
import com.ias.gateway.flight.FlightRepositoryFindGateway;
import com.ias.gateway.flight.FlightRepositorySaveGateway;
import com.ias.repositories.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FlightRepositoryAdapter implements FlightRepositoryFindGateway, FlightRepositorySaveGateway {

    private final FlightRepository flightRepository;

    public FlightRepositoryAdapter(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional
    public List<FlightDomain> findAll() {
        return flightRepository.findAll()
                .stream()
                .map(FlightDBO::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public FlightDomain findById(Long flightId) {
        return flightRepository.findById(flightId)
                .map(FlightDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found."));
    }

    @Override
    @Transactional
    public FlightDomain save(FlightDomain flightDomain) {
        return flightRepository.save(FlightDBO.fromDomain(flightDomain)).toDomain();
    }

    @Override
    @Transactional
    public List<FlightDomain> findAllByDate(LocalDateTime date) {
        return flightRepository.findAllByDate(date)
                .stream().map(FlightDBO::toDomain).toList();
    }
}
