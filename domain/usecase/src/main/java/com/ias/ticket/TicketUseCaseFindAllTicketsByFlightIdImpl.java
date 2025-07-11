package com.ias.ticket;

import com.ias.TicketDomain;
import com.ias.gateway.ticket.TicketRepositoryFindAllTicketsByFlightIdGateway;

import java.util.List;

public class TicketUseCaseFindAllTicketsByFlightIdImpl {

    private final TicketRepositoryFindAllTicketsByFlightIdGateway ticketRepositoryFindAllTicketsByFlightIdGateway;

    public TicketUseCaseFindAllTicketsByFlightIdImpl(TicketRepositoryFindAllTicketsByFlightIdGateway ticketRepositoryFindAllTicketsByFlightIdGateway) {
        this.ticketRepositoryFindAllTicketsByFlightIdGateway = ticketRepositoryFindAllTicketsByFlightIdGateway;
    }

    public List<TicketDomain> findAllTicketsByFlightId(Long flightId){
        return ticketRepositoryFindAllTicketsByFlightIdGateway.findAllTicketsByFlightId(flightId);
    }
}
