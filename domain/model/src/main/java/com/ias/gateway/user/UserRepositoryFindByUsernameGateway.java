package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositoryFindByUsernameGateway {
    UserDomain findByUsername(String username);
}
