package com.ias;

import com.ias.gateway.ticket.TicketRepositoryFindGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;

import java.util.List;

public class TicketUseCase {
    private final FlightService flightService;

    private final TicketRepositoryFindGateway ticketRepositoryFindGateway;
    private final TicketRepositorySaveGateway ticketRepositorySaveGateway;

    public TicketUseCase(FlightService flightService,
                         TicketRepositoryFindGateway ticketRepositoryFindGateway,
                         TicketRepositorySaveGateway ticketRepositorySaveGateway) {
        this.flightService = flightService;
        this.ticketRepositoryFindGateway = ticketRepositoryFindGateway;
        this.ticketRepositorySaveGateway = ticketRepositorySaveGateway;
    }

    public List<TicketDomain> getAllTicketsByFlightId(Long flightId){
        FlightDomain flightDomain = flightService.getFlightById(flightId);
        return ticketRepositoryFindGateway.findAllTicketsByFlightId(flightDomain.getId());
    }

    public TicketDomain createTicket(Long flightId, TicketDomain ticketDomain) {
        FlightDomain flightDomain = flightService.getFlightById(flightId);

        validateFlightIsFull(flightDomain);
        setFullToFlight(flightDomain);
        setFlightToTicket(ticketDomain, flightDomain);

        return ticketRepositorySaveGateway.save(flightId, ticketDomain);
    }

    public void setFullToFlight(FlightDomain flightDomain){
        if (flightDomain.getTickets().size() + 1 >= flightDomain.getCantSeats()) {
            flightDomain.setFull(true);
            flightService.saveFlight(flightDomain);
        }
    }

    private void setFlightToTicket(TicketDomain ticketDomain, FlightDomain flightDomain){
        ticketDomain.setFlightDomain(flightDomain);
    }

    public void validateFlightIsFull(FlightDomain flightDomain){
        if(flightDomain.isFull()){
            throw new FlightFullException("The flight is full.");
        }
    }
}
