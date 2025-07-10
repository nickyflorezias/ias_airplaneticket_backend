package com.ias.gateway;

import com.ias.UserDomain;

public interface UserRepositoryGateway {
    UserDomain save(UserDomain userDomain);
    UserDomain findById(Long userId);
    UserDomain findByEmail(String email);
    UserDomain findByUsername(String username);
    String login(UserDomain userDomain);
}
