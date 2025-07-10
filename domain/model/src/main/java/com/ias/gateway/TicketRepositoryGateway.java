package com.ias.gateway;

import com.ias.TicketDomain;

import java.util.List;

public interface TicketRepositoryGateway {
    List<TicketDomain> findAllTicketsByFlightId(Long flightId);
    TicketDomain findById(Long ticketId);
    TicketDomain save(Long flightId, TicketDomain ticketDomain);
}
