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
        UserDomain userFounded = userRepositoryGateway.findByEmail(userDomain.getEmail());

        if(!userDomain.getPassword().equals(userFounded.getPassword())){
            throw new IllegalArgumentException("Password doesn't match.");
        }
        return "Logged jwt";
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

        if(!userDomain.getEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            throw new UserIllegalArgumentException("Email is not valid.");
        }

        if(!userDomain.getPassword().matches("^(?=.{8,20}$)(?=.*[A-Za-z])(?=.*(?=.*[0-9]))(?=.*?[#?!@$%^&*-])(?!.*(.)\\1).+$")){
            throw new UserIllegalArgumentException("Password is not valid.");
        }
    }
}