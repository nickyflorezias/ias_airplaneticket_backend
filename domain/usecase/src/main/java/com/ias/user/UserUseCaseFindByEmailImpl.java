package com.ias.user;

import com.ias.UserDomain;
import com.ias.gateway.user.UserRepositoryFindByEmailGateway;

public class UserUseCaseFindByEmailImpl {

    private final UserRepositoryFindByEmailGateway userRepositoryFindByEmailGateway;

    public UserUseCaseFindByEmailImpl(UserRepositoryFindByEmailGateway userRepositoryFindByEmailGateway) {
        this.userRepositoryFindByEmailGateway = userRepositoryFindByEmailGateway;
    }

    public UserDomain findByEmail(String email){
        return userRepositoryFindByEmailGateway.findByEmail(email);
    }
}
