package com.ias.user;

import com.ias.UserDomain;
import com.ias.UserValidationService;
import com.ias.gateway.user.UserRepositorySaveGateway;

import java.util.logging.Logger;

public class UserUseCaseSaveImpl {

    private final Logger LOGGER = Logger.getLogger(UserUseCaseSaveImpl.class.getName());


    private final UserRepositorySaveGateway userRepositorySaveGateway;
    private final UserValidationService userValidationService;

    public UserUseCaseSaveImpl(UserRepositorySaveGateway userRepositorySaveGateway, UserValidationService userValidationService) {
        this.userRepositorySaveGateway = userRepositorySaveGateway;
        this.userValidationService = userValidationService;
    }

    public UserDomain registerUser(UserDomain userDomain){
        LOGGER.fine("Register user with " + userDomain.toString());
        LOGGER.info("Register user");

        userValidationService.validUsernameOnlyText(userDomain.getUsername());
        userValidationService.validUsernameLongerThanThirty(userDomain.getUsername());
        userValidationService.validEmailPattern(userDomain.getEmail());
        userValidationService.validPasswordPattern(userDomain.getPassword());

        return userRepositorySaveGateway.save(userDomain);
    }
}
