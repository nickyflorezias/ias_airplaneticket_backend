package com.ias;

import com.ias.gateway.user.UserRepositoryLoginGateway;
import com.ias.gateway.user.UserRepositorySaveGateway;

import java.util.logging.Logger;

public class UserUseCase {

    private final UserValidationService userValidationService;
    private final UserRepositorySaveGateway userRepositorySaveGateway;
    private final UserRepositoryLoginGateway userRepositoryLoginGateway;
    private final Logger logger = Logger.getLogger(UserUseCase.class.getName());

    public UserUseCase(UserValidationService userValidationService, UserRepositorySaveGateway userRepositorySaveGateway, UserRepositoryLoginGateway userRepositoryLoginGateway) {
        this.userValidationService = userValidationService;
        this.userRepositorySaveGateway = userRepositorySaveGateway;
        this.userRepositoryLoginGateway = userRepositoryLoginGateway;
    }

    public UserDomain registerUser(UserDomain userDomain){
        logger.fine("Register user with " + userDomain.toString());
        logger.info("Register user");

        userValidationService.validUsernameOnlyText(userDomain.getUsername());
        userValidationService.validUsernameLongerThanThirty(userDomain.getUsername());
        userValidationService.validEmailPattern(userDomain.getEmail());
        userValidationService.validPasswordPattern(userDomain.getPassword());

        return userRepositorySaveGateway.save(userDomain);
    }

    public String loginUser(UserDomain userDomain){
        return userRepositoryLoginGateway.login(userDomain);
    }
}