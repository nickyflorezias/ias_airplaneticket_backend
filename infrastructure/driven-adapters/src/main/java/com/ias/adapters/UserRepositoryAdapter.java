package com.ias.adapters;

import com.ias.UserDomain;
import com.ias.gateway.UserRepositoryGateway;
import com.ias.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepositoryGateway {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        return null;
    }

    @Override
    public UserDomain findById(Long userId) {
        return null;
    }

    @Override
    public UserDomain findByEmail(String email) {
        return null;
    }

    @Override
    public UserDomain findByUsername(String username) {
        return null;
    }
}
