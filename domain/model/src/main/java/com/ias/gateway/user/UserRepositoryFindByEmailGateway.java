package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositoryFindByEmailGateway {
    UserDomain findByEmail(String email);
}
