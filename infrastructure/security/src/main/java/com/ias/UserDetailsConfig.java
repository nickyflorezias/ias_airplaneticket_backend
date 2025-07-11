package com.ias;

import com.ias.gateway.user.UserRepositoryFindGateway;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsConfig implements UserDetailsService {

    private final UserRepositoryFindGateway userRepositoryFindGateway;

    public UserDetailsConfig(UserRepositoryFindGateway userRepositoryFindGateway) {
        this.userRepositoryFindGateway = userRepositoryFindGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain userDomain = userRepositoryFindGateway.findByUsername(username);
        return new User(
                userDomain.getUsername(),
                userDomain.getPassword(),
                new ArrayList<>()
        );
    }
}
