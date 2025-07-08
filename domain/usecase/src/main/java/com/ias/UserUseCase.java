package com.ias;

import com.ias.gateway.UserRepositoryGateway;

public class UserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public UserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public UserDomain registerUser(UserDomain userDomain){
        if(userDomain.getUsername() == null || userDomain.getEmail() == null || userDomain.getPassword() == null){
            throw new IllegalArgumentException("Invalid: all arguments must not be null.");
        }
        isValid(userDomain);

        return userRepositoryGateway.save(userDomain);
    }

    public String loginUser(UserDomain userDomain){
        return "";
    }

    public UserDomain getUserById(Long userId){
        return userRepositoryGateway.findById(userId);
    }

    public UserDomain getUserByUsername(String username){
        return userRepositoryGateway.findByUsername(username);
    }

    public UserDomain getUserByEmail(String email){
        return userRepositoryGateway.findByEmail(email);
    }


    private void isValid(UserDomain userDomain){
        if(userDomain.getUsername().length() > 30){
            throw new UserIllegalArgumentException("Username can't be longer than 30 characters");
        }

        if(!userDomain.getUsername().matches("^[A-Za-z]+$")){
            throw new UserIllegalArgumentException("Username must be only text.");
        }

        if(!userDomain.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$")){
            throw new UserIllegalArgumentException("Email is not valid.");
        }

        if(!userDomain.getPassword().matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z0-9]{6,}$")){
            throw new UserIllegalArgumentException("Password is not valid.");
        }
    }
}