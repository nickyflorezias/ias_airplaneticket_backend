package com.ias.adapters;

import com.ias.TicketDomain;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.repositories.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepositoryAdapter implements TicketRepositoryGateway {

    private final TicketRepository ticketRepository;

    public TicketRepositoryAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketDomain> findAllTicketsByReservationId(Long reservationId) {
        return List.of();
    }

    @Override
    public TicketDomain findById(Long ticketId) {
        return null;
    }

    @Override
    public TicketDomain save(TicketDomain ticketDomain) {
        return null;
    }
}
