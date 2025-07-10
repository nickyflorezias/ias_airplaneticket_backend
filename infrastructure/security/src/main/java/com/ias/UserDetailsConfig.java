package com.ias;

import com.ias.gateway.UserRepositoryGateway;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsConfig implements UserDetailsService {

    private final UserRepositoryGateway userRepository;

    public UserDetailsConfig(UserRepositoryGateway userRepositoryGateway) {
        this.userRepository = userRepositoryGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain userDomain = userRepository.findByUsername(username);

        System.out.println(userDomain);
        return new User(
                userDomain.getUsername(),
                userDomain.getPassword(),
                new ArrayList<>()
        );
    }
}
