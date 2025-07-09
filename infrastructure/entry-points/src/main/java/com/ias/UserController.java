package com.ias;

import com.fasterxml.jackson.annotation.JsonView;
import com.ias.dto.ResponseDTO;
import com.ias.dto.request.UserDTO;
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
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @JsonView(UserDTO.Views.Register.class) UserDTO user){
        UserDomain userDomain = new UserDomain(
                null,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                null,
                null
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        userUseCase.registerUser(userDomain),
                        HttpStatus.CREATED,
                        "User registered successfully."
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody @JsonView(UserDTO.Views.Login.class) UserDTO user){
        UserDomain userDomain = new UserDomain(
                null,
                null,
                user.getEmail(),
                user.getPassword(),
                null,
                null
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        userUseCase.loginUser(userDomain),
                        HttpStatus.OK,
                        "User logged successfully."
                ));
    }
}