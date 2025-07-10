package com.ias.adapters;

import com.ias.UserDomain;
import com.ias.dbo.UserDBO;
import com.ias.gateway.UserRepositoryGateway;
import com.ias.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepositoryGateway {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDomain save(UserDomain userDomain) {
        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        return userRepository.save(UserDBO.fromDomain(userDomain)).toDomain();
    }

    @Override
    @Transactional
    public UserDomain findById(Long userId) {
        return userRepository.findById(userId)
                .map(UserDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
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
        return userRepository.findByUsername(username)
                .map(UserDBO::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    @Override
    @Transactional
    public String login(UserDomain userDomain) {
        UserDomain userFounded = findByEmail(userDomain.getEmail());

        if(!passwordEncoder.matches(userDomain.getPassword(), userFounded.getPassword())){
            throw new IllegalArgumentException("Password doesn't match.");
        }

        return "Logged";
    }
}
