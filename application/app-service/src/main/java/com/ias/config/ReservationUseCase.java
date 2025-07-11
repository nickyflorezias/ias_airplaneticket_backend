package com.ias.config;

import com.ias.ReservationService;
import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;
import com.ias.gateway.reservation.*;
import com.ias.gateway.ticket.TicketRepositoryFindByIdGateway;
import com.ias.gateway.ticket.TicketRepositorySaveGateway;
import com.ias.gateway.user.UserRepositoryFindByIdGateway;
import com.ias.reservation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationUseCase {

    @Bean
    public ReservationService reservationService(){
        return new ReservationService();
    }

    @Bean
    public ReservationUseCaseFindByIdImpl reservationUseCaseFindById(ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway){
        return new ReservationUseCaseFindByIdImpl(reservationRepositoryFindByIdGateway);
    }

    @Bean
    public ReservationUseCaseFindByUserIdImpl reservationUseCaseFindByUserId(ReservationRepositoryFindByUserIdGateway reservationRepositoryFindByUserIdGateway){
        return new ReservationUseCaseFindByUserIdImpl(reservationRepositoryFindByUserIdGateway);
    }

    @Bean
    public ReservationUseCaseSaveImpl reservationUseCaseSave(ReservationRepositorySaveGateway reservationRepositorySaveGateway,
                                                             UserRepositoryFindByIdGateway userRepositoryFindByIdGateway,
                                                             TicketRepositoryFindByIdGateway ticketRepositoryFindByIdGateway){
        return new ReservationUseCaseSaveImpl(reservationRepositorySaveGateway, userRepositoryFindByIdGateway, ticketRepositoryFindByIdGateway);
    }

    @Bean
    public ReservationUseCaseUpdateImpl reservationUseCaseUpdate(ReservationRepositoryUpdateGateway reservationRepositoryUpdateGateway,
                                                                 ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway,
                                                                 ReservationService reservationService){
        return new ReservationUseCaseUpdateImpl(reservationRepositoryUpdateGateway, reservationRepositoryFindByIdGateway, reservationService);
    }

    @Bean
    public ReservationUseCaseUpdateDateImpl reservationUseCaseUpdateDate(ReservationRepositoryUpdateDateGateway reservationUseCaseUpdateDate,
                                                                         ReservationRepositoryFindByIdGateway reservationRepositoryFindByIdGateway,
                                                                         ReservationService reservationService,
                                                                         FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway,
                                                                         TicketRepositorySaveGateway ticketRepositorySaveGateway){
        return new ReservationUseCaseUpdateDateImpl(reservationUseCaseUpdateDate,reservationRepositoryFindByIdGateway, reservationService, flightRepositoryFindAllByDateGateway, ticketRepositorySaveGateway);
    }
}
