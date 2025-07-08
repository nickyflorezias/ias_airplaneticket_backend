package com.ias.gateway;

import com.ias.TicketDomain;

import java.util.List;

public interface TicketRepositoryGateway {
    List<TicketDomain> findAllTicketsByReservationId(Long reservationId);
    TicketDomain findById(Long ticketId);
    TicketDomain save(TicketDomain ticketDomain);
}
