package com.ias.adapters;

import com.ias.UserDomain;
import com.ias.dbo.UserDBO;
import com.ias.gateway.UserRepositoryGateway;
import com.ias.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepositoryGateway {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDomain save(UserDomain userDomain) {
        return userRepository.save(UserDBO.fromDomain(userDomain)).toDomain();
    }

    @Override
    @Transactional
    public UserDomain findById(Long userId) {
        return null;
    }

    @Override
    @Transactional
    public UserDomain findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    @Override
    @Transactional
    public UserDomain findByUsername(String username) {
        return null;
    }
}
