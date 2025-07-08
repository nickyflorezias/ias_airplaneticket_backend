package com.ias;

import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;

import java.util.List;

public class TicketUseCase {
    private final TicketRepositoryGateway ticketRepositoryGateway;
    private final FlightRepositoryGateway flightRepositoryGateway;

    public TicketUseCase(TicketRepositoryGateway ticketRepositoryGateway, FlightRepositoryGateway flightRepositoryGateway) {
        this.ticketRepositoryGateway = ticketRepositoryGateway;
        this.flightRepositoryGateway = flightRepositoryGateway;
    }

    public List<TicketDomain> getAllTicketsByReservationId(Long reservationId){
        return ticketRepositoryGateway.findAllTicketsByReservationId(reservationId);
    }

    public TicketDomain getById(Long ticketId){
        return ticketRepositoryGateway.findById(ticketId);
    }

    public TicketDomain createTicket(TicketDomain ticketDomain){
        FlightDomain flightDomain = flightRepositoryGateway.findById(ticketDomain.getFlight().getId());
        if(flightDomain.isFull()){
            throw new FlightFullException("The flight is full.");
        }
        return ticketRepositoryGateway.save(ticketDomain);
    }
}
