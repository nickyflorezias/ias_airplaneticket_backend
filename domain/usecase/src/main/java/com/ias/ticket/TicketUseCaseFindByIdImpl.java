package com.ias.ticket;

import com.ias.TicketDomain;
import com.ias.gateway.ticket.TicketRepositoryFindByIdGateway;

public class TicketUseCaseFindByIdImpl {

    private final TicketRepositoryFindByIdGateway ticketRepositoryFindByIdGateway;

    public TicketUseCaseFindByIdImpl(TicketRepositoryFindByIdGateway ticketRepositoryFindByIdGateway) {
        this.ticketRepositoryFindByIdGateway = ticketRepositoryFindByIdGateway;
    }

    public TicketDomain findById(Long ticketId){
        return ticketRepositoryFindByIdGateway.findById(ticketId);
    }
}
