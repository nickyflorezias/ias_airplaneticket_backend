package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.ias.UserDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @JsonView(Views.Register.class)
    @NotNull(message = "Username can't be null") @Size(min = 1, max = 30, message = "Username should be longer than 0 but less than 31")
    private String username;

    @NotNull(message = "Email can't be null") @Email(message = "Email should be an email test@ias.com")
    @JsonView(Views.Login.class)
    private String email;

    @NotNull(message = "Password can't be null") @Size(min = 6, message = "Password length should be longer than 5")
    @JsonView(Views.Login.class)
    private String password;

    private List<ReservationDTO> reservations;

    public static class Views {
        public static class Login {}
        public static class Register extends Login {}
    }

    public static UserDTO fromDomain(UserDomain domain){
        return new UserDTO(
                domain.getId(),
                domain.getUsername(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getReservations() != null ? domain.getReservations().stream()
                        .map(reservationDomain -> new ReservationDTO(
                                reservationDomain.getId(),
                                reservationDomain.getStatus(),
                                reservationDomain.getTicket() != null ? new TicketDTO(
                                        reservationDomain.getTicket().getId(),
                                        reservationDomain.getTicket().getFlight() != null ? new FlightDTO(
                                                reservationDomain.getTicket().getFlight().getId(),
                                                reservationDomain.getTicket().getFlight().isFull(),
                                                reservationDomain.getTicket().getFlight().getName(),
                                                reservationDomain.getTicket().getFlight().getOriginCity(),
                                                reservationDomain.getTicket().getFlight().getDestinyCity(),
                                                reservationDomain.getTicket().getFlight().getDate(),
                                                reservationDomain.getTicket().getFlight().getPlaneName(),
                                                reservationDomain.getTicket().getFlight().getType(),
                                                reservationDomain.getTicket().getFlight().getAirlineName(),
                                                reservationDomain.getTicket().getFlight().getCantSeats()
                                        ) : null,
                                        reservationDomain.getTicket().getSeat(),
                                        reservationDomain.getTicket().getSeatClass()
                                ) : null,
                                reservationDomain.getDate(),
                                reservationDomain.getDescription()
                        )).toList() : new ArrayList<>()
        );
    }

    public UserDomain toDomain(){
        return new UserDomain(
                getId(),
                getUsername() != null ? getUsername() : null,
                getEmail(),
                getPassword(),
                null,
                null
        );
    }

}
