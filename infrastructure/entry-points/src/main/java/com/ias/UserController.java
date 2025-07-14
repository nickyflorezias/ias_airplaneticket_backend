package com.ias;

import com.fasterxml.jackson.annotation.JsonView;
import com.ias.dto.ResponseDTO;
import com.ias.dto.request.UserDTO;
import com.ias.enums.HttpStatusCodeCustom;
import com.ias.user.UserUseCaseLoginImpl;
import com.ias.user.UserUseCaseSaveImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCaseSaveImpl userUseCaseSave;
    private final UserUseCaseLoginImpl userUseCaseLogin;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @JsonView(UserDTO.Views.Register.class) UserDTO user){

        UserDomain domainResponse = userUseCaseSave.registerUser(user.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        UserDTO.fromDomain(domainResponse),
                        HttpStatusCodeCustom.CREATED_201,
                        "User registered successfully."
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody @JsonView(UserDTO.Views.Login.class) UserDTO user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(
                        userUseCaseLogin.loginUser(user.toDomain()),
                        HttpStatusCodeCustom.OK_200,
                        "User logged successfully."
                ));
    }
}