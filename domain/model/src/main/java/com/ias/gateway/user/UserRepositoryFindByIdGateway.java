package com.ias.gateway.user;

import com.ias.UserDomain;

public interface UserRepositoryFindByIdGateway {
    UserDomain findById(Long userId);
}
