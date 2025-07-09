package com.ias.config;

import com.ias.FlightUseCase;
import com.ias.ReservationUseCase;
import com.ias.TicketUseCase;
import com.ias.UserUseCase;
import com.ias.gateway.FlightRepositoryGateway;
import com.ias.gateway.ReservationRepositoryGateway;
import com.ias.gateway.TicketRepositoryGateway;
import com.ias.gateway.UserRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBean {

    @Bean
    public UserUseCase userUseCase(UserRepositoryGateway userRepositoryGateway){
        return new UserUseCase(userRepositoryGateway);
    }

    @Bean
    public FlightUseCase flightUseCase(FlightRepositoryGateway flightRepositoryGateway){
        return new FlightUseCase(flightRepositoryGateway);
    }

    @Bean
    public TicketUseCase ticketUseCase(TicketRepositoryGateway ticketRepositoryGateway, FlightRepositoryGateway flightRepositoryGateway, UserRepositoryGateway userRepositoryGateway){
        return new TicketUseCase(ticketRepositoryGateway, flightRepositoryGateway, userRepositoryGateway);
    }

    @Bean
    public ReservationUseCase reservationUseCase(UserRepositoryGateway userRepositoryGateway, ReservationRepositoryGateway reservationRepositoryGateway, TicketRepositoryGateway ticketRepositoryGateway){
        return new ReservationUseCase(reservationRepositoryGateway, userRepositoryGateway, ticketRepositoryGateway);
    }
}
