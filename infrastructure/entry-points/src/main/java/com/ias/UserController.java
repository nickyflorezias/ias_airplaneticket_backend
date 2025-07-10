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

        UserDomain domainResponse = userUseCase.registerUser(user.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        UserDTO.fromDomain(domainResponse),
                        HttpStatus.CREATED,
                        "User registered successfully."
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody @JsonView(UserDTO.Views.Login.class) UserDTO user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        userUseCase.loginUser(user.toDomain()),
                        HttpStatus.OK,
                        "User logged successfully."
                ));
    }
}