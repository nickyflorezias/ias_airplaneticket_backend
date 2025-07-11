package com.ias.adapters;

import com.ias.UserDomain;
import com.ias.dbo.UserDBO;
import com.ias.gateway.user.*;
import com.ias.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepositoryFindByIdGateway, UserRepositoryFindByEmailGateway, UserRepositoryFindByUsernameGateway, UserRepositorySaveGateway, UserRepositoryLoginGateway {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryAdapter(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDomain save(UserDomain userDomain) {
        encryptUserPassword(userDomain);
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
        passwordMatch(userDomain.getPassword(), userFounded.getPassword());
        return "Logged";
    }

    private void encryptUserPassword(UserDomain userDomain){
        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
    }

    private void passwordMatch(String rawPassword, String encodedPassword){
        if(!passwordEncoder.matches(rawPassword, encodedPassword)){
            throw new IllegalArgumentException("Password doesn't match.");
        }
    }
}
