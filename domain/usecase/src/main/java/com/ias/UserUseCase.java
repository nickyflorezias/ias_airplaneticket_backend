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

        validUsernameOnlyText(userDomain.getUsername());
        validUsernameLongerThanThirty(userDomain.getUsername());
        validEmailPattern(userDomain.getEmail());
        validPasswordPattern(userDomain.getPassword());

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

    private void validUsernameLongerThanThirty(String username){
        if(!username.matches("^.{1,30}$")){
            logger.severe("User Username not valid, can't be longer than 30 characters " + username);
            throw new UserIllegalArgumentException("Username can't be longer than 30 characters");
        }
    }

    public void validUsernameOnlyText(String username){
        if(!username.matches("^[A-Za-z]+$")){
            logger.severe("User Username not valid,  must be only text " + username);
            throw new UserIllegalArgumentException("Username must be only text.");
        }
    }

    public void validEmailPattern(String email){
        if(!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            logger.severe("User Email not valid, no pattern valid " + email);
            throw new UserIllegalArgumentException("Email is not valid.");
        }
    }

    public void validPasswordPattern(String password){
        if(!password.matches("^(?=.{8,20}$)(?=.*[A-Za-z])(?=.*(?=.*[0-9]))(?=.*?[#?!@$%^&*-])(?!.*(.)\\1).+$")){
            logger.severe("User Password not valid, no pattern valid " + password);
            throw new UserIllegalArgumentException("Password is not valid.");
        }
    }
}