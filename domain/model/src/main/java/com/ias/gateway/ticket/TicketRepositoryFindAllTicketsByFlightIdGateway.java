package com.ias.gateway.ticket;

import com.ias.TicketDomain;

import java.util.List;

public interface TicketRepositoryFindAllTicketsByFlightIdGateway {
    List<TicketDomain> findAllTicketsByFlightId(Long flightId);
}
