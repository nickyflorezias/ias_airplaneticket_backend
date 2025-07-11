package com.ias.gateway.ticket;

import com.ias.TicketDomain;

public interface TicketRepositoryFindByIdGateway {
    TicketDomain findById(Long ticketId);
}
