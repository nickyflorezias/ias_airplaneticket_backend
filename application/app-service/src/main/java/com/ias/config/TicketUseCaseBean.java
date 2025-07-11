package com.ias.config;

import com.ias.FlightService;
import com.ias.adapters.TicketRepositoryAdapter;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;
import com.ias.ticket.TicketUseCaseFindAllTicketsByFlightIdImpl;
import com.ias.ticket.TicketUseCaseFindByIdImpl;
import com.ias.ticket.TicketUseCaseSaveImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketUseCaseBean {

    @Bean
    public TicketUseCaseFindByIdImpl ticketUseCaseFindById(TicketRepositoryAdapter ticketRepositoryFindByIdGateway){
        return new TicketUseCaseFindByIdImpl(ticketRepositoryFindByIdGateway);
    }

    @Bean
    public TicketUseCaseFindAllTicketsByFlightIdImpl tickets(TicketRepositoryAdapter tickets){
        return new TicketUseCaseFindAllTicketsByFlightIdImpl(tickets);
    }

    @Bean
    public TicketUseCaseSaveImpl ticketUseCaseSave(TicketRepositorySaveGateway ticketRepositorySaveGateway, FlightService flightService){
        return new TicketUseCaseSaveImpl(ticketRepositorySaveGateway, flightService);
    }
}
