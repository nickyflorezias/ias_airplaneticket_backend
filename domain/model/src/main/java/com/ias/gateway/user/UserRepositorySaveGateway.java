package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositorySaveGateway {
    UserDomain save(UserDomain userDomain);
}
