package com.ias;

import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;

import java.util.List;

public class TicketUseCase {
    private final TicketRepositoryGateway ticketRepositoryGateway;
    private final FlightRepositoryGateway flightRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    public TicketUseCase(TicketRepositoryGateway ticketRepositoryGateway, FlightRepositoryGateway flightRepositoryGateway, UserRepositoryGateway userRepositoryGateway) {
        this.ticketRepositoryGateway = ticketRepositoryGateway;
        this.flightRepositoryGateway = flightRepositoryGateway;
        this.userRepositoryGateway = userRepositoryGateway;
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

    public TicketDomain createTicket(Long flightId, TicketDomain ticketDomain) {
        FlightDomain flightDomain = flightRepositoryGateway.findById(flightId);

        if (flightDomain.isFull()) {
            throw new FlightFullException("The flight is full.");
        }

        TicketDomain ticket = new TicketDomain(
                ticketDomain.getId(),
                ticketDomain.getSeat() != null ? ticketDomain.getSeat() : null,
                flightDomain,
                null,
                ticketDomain.getReservation() != null ? ticketDomain.getReservation() : null
        );

        TicketDomain savedTicket = ticketRepositoryGateway.save(flightId, ticket);

        if (flightDomain.getTickets().size() + 1 >= flightDomain.getCantSeats()) {
            flightDomain.setFull(true);
            flightRepositoryGateway.save(flightDomain);
        }

        return savedTicket;
    }
}
