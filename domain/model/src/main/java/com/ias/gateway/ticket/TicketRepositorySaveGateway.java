package com.ias.gateway.ticket;

import com.ias.TicketDomain;

public interface TicketRepositorySaveGateway {
    TicketDomain save(Long flightId, TicketDomain ticketDomain);
}
