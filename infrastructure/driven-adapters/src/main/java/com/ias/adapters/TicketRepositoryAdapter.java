package com.ias.adapters;

import com.ias.TicketDomain;
import com.ias.dbo.TicketDBO;
import com.ias.gateway.ticket.TicketRepositoryFindAllTicketsByFlightIdGateway;
import com.ias.gateway.ticket.TicketRepositoryFindByIdGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;
import com.ias.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor

@Repository
public class TicketRepositoryAdapter implements TicketRepositoryFindByIdGateway, TicketRepositoryFindAllTicketsByFlightIdGateway, TicketRepositorySaveGateway {

    private final TicketRepository ticketRepository;

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
        return ticketRepository.save(TicketDBO.fromDomain(ticketDomain)).toDomain();
    }
}
