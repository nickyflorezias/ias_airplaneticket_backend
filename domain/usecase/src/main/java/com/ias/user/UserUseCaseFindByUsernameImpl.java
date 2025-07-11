package com.ias.user;

import com.ias.UserDomain;
import com.ias.gateway.user.UserRepositoryFindByUsernameGateway;

public class UserUseCaseFindByUsernameImpl {

    private final UserRepositoryFindByUsernameGateway userRepositoryFindByUsernameGateway;

    public UserUseCaseFindByUsernameImpl(UserRepositoryFindByUsernameGateway userRepositoryFindByUsernameGateway) {
        this.userRepositoryFindByUsernameGateway = userRepositoryFindByUsernameGateway;
    }

    public UserDomain findByUsername(String username){
        return userRepositoryFindByUsernameGateway.findByUsername(username);
    }
}
