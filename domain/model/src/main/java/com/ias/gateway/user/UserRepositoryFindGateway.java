package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositoryFindGateway {
    UserDomain findById(Long userId);
    UserDomain findByEmail(String email);
        UserDomain findByUsername(String username);
}
