package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    @JsonView(Views.Register.class)
    @NotNull @Size(min = 1, max = 30)
    private String username;

    @NotNull @Email
    @JsonView(Views.Login.class)
    private String email;

    @NotNull @Size(min = 6)
    @JsonView(Views.Login.class)
    private String password;

    public class Views {
        public static class Login {}
        public static class Register extends Login {}
    }

}
