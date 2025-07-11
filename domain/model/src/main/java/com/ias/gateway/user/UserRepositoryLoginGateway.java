package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositoryLoginGateway {
    String login(UserDomain userDomain);
}
