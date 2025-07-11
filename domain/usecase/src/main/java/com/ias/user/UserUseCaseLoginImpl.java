package com.ias.user;

import com.ias.UserDomain;
import com.ias.gateway.user.UserRepositoryLoginGateway;

public class UserUseCaseLoginImpl {

    private final UserRepositoryLoginGateway userRepositoryLoginGateway;

    public UserUseCaseLoginImpl(UserRepositoryLoginGateway userRepositoryLoginGateway) {
        this.userRepositoryLoginGateway = userRepositoryLoginGateway;
    }

    public String loginUser(UserDomain userDomain){
        return userRepositoryLoginGateway.login(userDomain);
    }
}
