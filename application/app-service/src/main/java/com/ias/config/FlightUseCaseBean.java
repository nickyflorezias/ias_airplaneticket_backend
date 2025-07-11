package com.ias.config;

import com.ias.FlightService;
import com.ias.flight.FlightUseCaseFindAllByDateImpl;
import com.ias.flight.FlightUseCaseFindAllImpl;
import com.ias.flight.FlightUseCaseFindByIdImpl;
import com.ias.flight.FlightUseCaseSaveImpl;
import com.ias.gateway.flight.FlightRepositoryFindAllByDateGateway;
import com.ias.gateway.flight.FlightRepositoryFindAllGateway;
import com.ias.gateway.flight.FlightRepositoryFindByIdGateway;
import com.ias.gateway.flight.FlightRepositorySaveGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightUseCaseBean {

    @Bean
    public FlightService flightService(FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway, FlightRepositorySaveGateway flightRepositorySaveGateway, FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway){
        return new FlightService(flightRepositoryFindByIdGateway, flightRepositorySaveGateway, flightRepositoryFindAllByDateGateway);
    }

    @Bean
    public FlightUseCaseFindAllByDateImpl flightUseCaseFindAllByDate(FlightRepositoryFindAllByDateGateway flightRepositoryFindAllByDateGateway){
        return new FlightUseCaseFindAllByDateImpl(flightRepositoryFindAllByDateGateway);
    }

    @Bean
    public FlightUseCaseFindAllImpl flightUseCaseFindAll(FlightRepositoryFindAllGateway flightRepositoryFindAllGateway){
        return new FlightUseCaseFindAllImpl(flightRepositoryFindAllGateway);
    }

    @Bean
    public FlightUseCaseFindByIdImpl flightUseCaseFindById(FlightRepositoryFindByIdGateway flightRepositoryFindByIdGateway){
        return new FlightUseCaseFindByIdImpl(flightRepositoryFindByIdGateway);
    }

    @Bean
    public FlightUseCaseSaveImpl flightUseCaseSave(FlightRepositorySaveGateway flightRepositorySaveGateway, FlightService flightService){
        return new FlightUseCaseSaveImpl(flightRepositorySaveGateway, flightService);
    }
}
