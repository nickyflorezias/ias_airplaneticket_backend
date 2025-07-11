package com.ias.ticket;

import com.ias.FlightDomain;
import com.ias.FlightFullException;
import com.ias.FlightService;
import com.ias.TicketDomain;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;

public class TicketUseCaseSaveImpl {

    private final TicketRepositorySaveGateway ticketRepositorySaveGateway;
    private final FlightService flightService;

    public TicketUseCaseSaveImpl(TicketRepositorySaveGateway ticketRepositorySaveGateway, FlightService flightService) {
        this.ticketRepositorySaveGateway = ticketRepositorySaveGateway;
        this.flightService = flightService;
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
