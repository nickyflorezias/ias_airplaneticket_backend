package com.ias.gateway.ticket;

import com.ias.TicketDomain;

import java.util.List;

public interface TicketRepositoryFindGateway {
    List<TicketDomain> findAllTicketsByFlightId(Long flightId);
    TicketDomain findById(Long ticketId);
}
