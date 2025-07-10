package com.ias.adapters;

import com.ias.TicketDomain;
import com.ias.dbo.TicketDBO;
import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepositoryAdapter implements TicketRepositoryGateway {

    private final TicketRepository ticketRepository;
    private final FlightRepositoryGateway flightRepository;

    public TicketRepositoryAdapter(TicketRepository ticketRepository, FlightRepositoryGateway flightRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional
    public List<TicketDomain> findAllTicketsByFlightId(Long flightId) {
        return ticketRepository.findAll()
                .stream()
                .filter(ticketDBO -> ticketDBO.getFlight().getId().equals(flightId))
                .map(TicketDBO::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public TicketDomain findById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(TicketDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }

    @Override
    @Transactional
    public TicketDomain save(Long flightId, TicketDomain ticketDomain) {
        flightRepository.findById(flightId);
        return ticketRepository.save(TicketDBO.fromDomain(ticketDomain)).toDomain();
    }
}
