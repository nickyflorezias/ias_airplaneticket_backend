package com.ias.config;

import com.ias.FlightService;
import com.ias.adapters.FlightRepositoryAdapter;
import com.ias.flight.FlightUseCaseFindAllByDateImpl;
import com.ias.flight.FlightUseCaseFindAllImpl;
import com.ias.flight.FlightUseCaseFindByIdImpl;
import com.ias.flight.FlightUseCaseSaveImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightUseCaseBean {

    @Bean
    public FlightService flightService(FlightRepositoryAdapter flightRepositoryFindByIdGateway,
                                       FlightRepositoryAdapter flightRepositorySaveGateway){
        return new FlightService(flightRepositoryFindByIdGateway, flightRepositorySaveGateway);
    }

    @Bean
    public FlightUseCaseFindAllByDateImpl flightUseCaseFindAllByDate(FlightRepositoryAdapter flightRepositoryFindAllByDateGateway){
        return new FlightUseCaseFindAllByDateImpl(flightRepositoryFindAllByDateGateway);
    }

    @Bean
    public FlightUseCaseFindAllImpl flightUseCaseFindAll(FlightRepositoryAdapter flightRepositoryFindAllGateway){
        return new FlightUseCaseFindAllImpl(flightRepositoryFindAllGateway);
    }

    @Bean
    public FlightUseCaseFindByIdImpl flightUseCaseFindById(FlightRepositoryAdapter flightRepositoryFindByIdGateway){
        return new FlightUseCaseFindByIdImpl(flightRepositoryFindByIdGateway);
    }

    @Bean
    public FlightUseCaseSaveImpl flightUseCaseSave(FlightRepositoryAdapter flightRepositorySaveGateway,
                                                   FlightService flightService){
        return new FlightUseCaseSaveImpl(flightRepositorySaveGateway, flightService);
    }
}
