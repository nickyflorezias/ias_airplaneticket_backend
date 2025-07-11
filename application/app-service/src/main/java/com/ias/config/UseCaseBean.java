package com.ias.config;

import com.ias.*;
import com.ias.adapters.FlightRepositoryAdapter;
import com.ias.adapters.TicketRepositoryAdapter;
import com.ias.adapters.UserRepositoryAdapter;
import com.ias.gateway.flight.FlightRepositoryFindGateway;
import com.ias.gateway.flight.FlightRepositorySaveGateway;
import com.ias.gateway.reservation.ReservationRepositoryFindGateway;
import com.ias.gateway.reservation.ReservationRepositorySaveGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateDateGateway;
import com.ias.gateway.reservation.ReservationRepositoryUpdateGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBean {

    @Bean
    public UserValidationService userValidationService(){
        return new UserValidationService();
    }

    @Bean
    public FlightService findFlightService(FlightRepositoryFindGateway flightRepositoryFindGateway,
                                           FlightRepositorySaveGateway flightRepositorySaveGateway){
        return new FlightService(flightRepositoryFindGateway, flightRepositorySaveGateway);
    }

    @Bean
    public UserUseCase userUseCase(UserValidationService userValidationService,
                                   UserRepositoryAdapter userRepositorySaveGateway,
                                   UserRepositoryAdapter userRepositoryLoginGateway){
        return new UserUseCase(userValidationService, userRepositorySaveGateway, userRepositoryLoginGateway);
    }

    @Bean
    public FlightUseCase flightUseCase(FlightService flightService, FlightRepositoryFindGateway flightRepositoryGateway){
        return new FlightUseCase(flightService, flightRepositoryGateway);
    }

    @Bean
    public TicketUseCase ticketUseCase(FlightService flightService,
                                       TicketRepositoryAdapter ticketRepositoryFindGateway,
                                       TicketRepositoryAdapter ticketRepositorySaveGateway){
        return new TicketUseCase(flightService, ticketRepositoryFindGateway, ticketRepositorySaveGateway);
    }

    @Bean
    public ReservationUseCase reservationUseCase(ReservationRepositoryFindGateway reservationRepositoryFindGateway,
                                                 ReservationRepositorySaveGateway reservationRepositorySaveGateway,
                                                 ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway,
                                                 ReservationRepositoryUpdateDateGateway reservationRepositoryUpdateDateGateway,
                                                 UserRepositoryAdapter userRepositoryFindGateway,
                                                 FlightRepositoryAdapter flightRepositoryFindGateway,
                                                 TicketRepositoryAdapter ticketRepositoryFindGateway,
                                                 TicketRepositoryAdapter ticketRepositorySaveGateway){
        return new ReservationUseCase(reservationRepositoryFindGateway,
                reservationRepositorySaveGateway,
                reservationRepositoryUpdateGateway,
                reservationRepositoryUpdateDateGateway,
                userRepositoryFindGateway,
                flightRepositoryFindGateway,
                ticketRepositoryFindGateway,
                ticketRepositorySaveGateway);
    }
}
