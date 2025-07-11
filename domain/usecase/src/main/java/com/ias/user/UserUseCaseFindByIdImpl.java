package com.ias.user;

import com.ias.UserDomain;
import com.ias.gateway.user.UserRepositoryFindByIdGateway;

public class UserUseCaseFindByIdImpl {

    private final UserRepositoryFindByIdGateway userRepositoryFindByIdGateway;

    public UserUseCaseFindByIdImpl(UserRepositoryFindByIdGateway userRepositoryFindByIdGateway) {
        this.userRepositoryFindByIdGateway = userRepositoryFindByIdGateway;
    }

    public UserDomain findById(Long userId){
        return userRepositoryFindByIdGateway.findById(userId);
    }

}
