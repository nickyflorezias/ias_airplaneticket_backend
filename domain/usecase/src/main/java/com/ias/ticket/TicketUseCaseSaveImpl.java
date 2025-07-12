package com.ias.ticket;

import com.ias.FlightDomain;
import com.ias.FlightService;
import com.ias.TicketDomain;
import com.ias.enums.FlightStatus;
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

        flightService.validateFlightIsFull(flightDomain);
        setFullToFlight(flightDomain);
        flightService.setFlightToTicket(ticketDomain, flightDomain);

        return ticketRepositorySaveGateway.save(flightId, ticketDomain);
    }
    public void setFullToFlight(FlightDomain flightDomain){
        if (flightDomain.getTickets().size() + 1 >= flightDomain.getCantSeats()) {
            flightDomain.setStatus(FlightStatus.FULL);
            flightService.saveFlight(flightDomain);
        }
    }

}
