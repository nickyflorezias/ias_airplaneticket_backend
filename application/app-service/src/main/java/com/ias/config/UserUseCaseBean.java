package com.ias.config;

import com.ias.UserValidationService;
import com.ias.adapters.UserRepositoryAdapter;
import com.ias.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseBean {

    @Bean
    public UserValidationService userValidationService(){
        return new UserValidationService();
    }

    @Bean
    public UserUseCaseFindByIdImpl userUseCaseFindById(UserRepositoryAdapter userRepositoryFindByIdGateway){
        return new UserUseCaseFindByIdImpl(userRepositoryFindByIdGateway);
    }

    @Bean
    public UserUseCaseFindByUsernameImpl userUseCaseFindByUsername(UserRepositoryAdapter userRepositoryFindByIdGateway){
        return new UserUseCaseFindByUsernameImpl(userRepositoryFindByIdGateway);
    }

    @Bean
    public UserUseCaseFindByEmailImpl userUseCaseFindByEmail(UserRepositoryAdapter userRepositoryFindByIdGateway){
        return new UserUseCaseFindByEmailImpl(userRepositoryFindByIdGateway);
    }

    @Bean
    public UserUseCaseSaveImpl userUseCaseSave(UserRepositoryAdapter userRepositoryFindByIdGateway,
                                               UserValidationService userValidationService){
        return new UserUseCaseSaveImpl(userRepositoryFindByIdGateway, userValidationService);
    }

    @Bean
    public UserUseCaseLoginImpl userUseCaseLogin(UserRepositoryAdapter userRepositoryFindByIdGateway){
        return new UserUseCaseLoginImpl(userRepositoryFindByIdGateway);
    }
}
