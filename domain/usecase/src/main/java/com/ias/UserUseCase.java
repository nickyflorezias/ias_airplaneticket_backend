package com.ias;

import com.ias.gateway.UserRepositoryGateway;

import java.util.logging.Logger;

public class UserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    private final Logger logger = Logger.getLogger(UserUseCase.class.getName());

    public UserUseCase(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public UserDomain registerUser(UserDomain userDomain){
        logger.fine("Register user with " + userDomain.toString());
        logger.info("Register user");
        if(userDomain.getUsername() == null || userDomain.getEmail() == null || userDomain.getPassword() == null){
            throw new IllegalArgumentException("Invalid: all arguments must not be null.");
        }
        isValid(userDomain);

        return userRepositoryGateway.save(userDomain);
    }

    public String loginUser(UserDomain userDomain){
        UserDomain userFounded = userRepositoryGateway.findByEmail(userDomain.getEmail());

        if(!userDomain.getPassword().equals(userFounded.getPassword())){
            logger.severe("Password doesn't match with input " + userDomain.getPassword() + " and saved: " + userFounded.getPassword());
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
            logger.severe("User Username not valid, can't be longer than 30 characters " + userDomain.getUsername());
            throw new UserIllegalArgumentException("Username can't be longer than 30 characters");
        }

        if(!userDomain.getUsername().matches("^[A-Za-z]+$")){
            logger.severe("User Username not valid,  must be only text " + userDomain.getUsername());
            throw new UserIllegalArgumentException("Username must be only text.");
        }

        if(!userDomain.getEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            logger.severe("User Email not valid, no pattern valid " + userDomain.getEmail());
            throw new UserIllegalArgumentException("Email is not valid.");
        }

        if(!userDomain.getPassword().matches("^(?=.{8,20}$)(?=.*[A-Za-z])(?=.*(?=.*[0-9]))(?=.*?[#?!@$%^&*-])(?!.*(.)\\1).+$")){
            logger.severe("User Password not valid, no pattern valid " + userDomain.getPassword());
            throw new UserIllegalArgumentException("Password is not valid.");
        }
    }
}