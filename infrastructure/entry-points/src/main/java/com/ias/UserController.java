package com.ias;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDomain> registerUser(@RequestBody UserDomain user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDomain user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.loginUser(user));
    }
}