package com.ias;

import java.util.logging.Logger;

public class UserValidationService {

    private final Logger logger = Logger.getLogger(UserValidationService.class.getName());

    public void validUsernameLongerThanThirty(String username){
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
