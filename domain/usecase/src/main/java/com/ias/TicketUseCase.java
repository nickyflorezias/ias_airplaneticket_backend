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

    public List<TicketDomain> getAllTicketsByFlightId(Long flightId){
        FlightDomain flightDomain = flightRepositoryGateway.findById(flightId);

        return ticketRepositoryGateway.findAllTicketsByFlightId(flightDomain.getId());
    }

    public TicketDomain getById(Long ticketId){
        return ticketRepositoryGateway.findById(ticketId);
    }

    public TicketDomain createTicket(Long flightId, TicketDomain ticketDomain){
        FlightDomain flightDomain = flightRepositoryGateway.findById(flightId);
        if(flightDomain.isFull()){
            throw new FlightFullException("The flight is full.");
        }
        TicketDomain ticket = new TicketDomain(
                ticketDomain.getId(),
                ticketDomain.getDate() != null ? ticketDomain.getDate() : null,
                flightDomain,
                ticketDomain.getReservation() != null ? ticketDomain.getReservation() : null
        );
        return ticketRepositoryGateway.save(flightId, ticket);
    }
}
