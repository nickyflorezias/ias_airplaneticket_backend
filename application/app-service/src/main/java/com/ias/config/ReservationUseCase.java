package com.ias.config;

import com.ias.ReservationService;
import com.ias.adapters.FlightRepositoryAdapter;
import com.ias.adapters.ReservationRepositoryAdapter;
import com.ias.adapters.TicketRepositoryAdapter;
import com.ias.adapters.UserRepositoryAdapter;
import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;
import com.ias.gateway.reservation.*;
import com.ias.gateway.ticket.TicketRepositoryFindByIdGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;
import com.ias.gateway.user.UserRepositoryFindByIdGateway;
import com.ias.reservation.*;
import com.ias.ticket.TicketUseCaseFindByIdImpl;
import com.ias.user.UserUseCaseFindByIdImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationUseCase {

    @Bean
    public ReservationService reservationService(){
        return new ReservationService();
    }

    @Bean
    public ReservationUseCaseFindByIdImpl reservationUseCaseFindById(ReservationRepositoryAdapter reservationRepositoryFindByIdGateway){
        return new ReservationUseCaseFindByIdImpl(reservationRepositoryFindByIdGateway);
    }

    @Bean
    public ReservationUseCaseFindByUserIdImpl reservationUseCaseFindByUserId(ReservationRepositoryAdapter reservationRepositoryFindByUserIdGateway){
        return new ReservationUseCaseFindByUserIdImpl(reservationRepositoryFindByUserIdGateway);
    }

    @Bean
    public ReservationUseCaseSaveImpl reservationUseCaseSave(ReservationRepositoryAdapter reservationRepositorySaveGateway,
                                                             UserUseCaseFindByIdImpl userRepositoryFindByIdGateway,
                                                             TicketUseCaseFindByIdImpl ticketRepositoryFindByIdGateway){
        return new ReservationUseCaseSaveImpl(reservationRepositorySaveGateway, userRepositoryFindByIdGateway, ticketRepositoryFindByIdGateway);
    }

    @Bean
    public ReservationUseCaseUpdateImpl reservationUseCaseUpdate(ReservationRepositoryAdapter reservationRepositoryUpdateGateway,
                                                                 ReservationRepositoryAdapter reservationRepositoryFindByIdGateway,
                                                                 ReservationService reservationService){
        return new ReservationUseCaseUpdateImpl(reservationRepositoryUpdateGateway, reservationRepositoryFindByIdGateway, reservationService);
    }

    @Bean
    public ReservationUseCaseUpdateDateImpl reservationUseCaseUpdateDate(ReservationRepositoryAdapter reservationUseCaseUpdateDate,
                                                                         ReservationRepositoryAdapter reservationRepositoryFindByIdGateway,
                                                                         ReservationService reservationService,
                                                                         FlightRepositoryAdapter flightRepositoryFindAllByDateGateway,
                                                                         TicketRepositoryAdapter ticketRepositorySaveGateway){
        return new ReservationUseCaseUpdateDateImpl(reservationUseCaseUpdateDate,reservationRepositoryFindByIdGateway, reservationService, flightRepositoryFindAllByDateGateway, ticketRepositorySaveGateway);
    }
}
