package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ias.ReservationDomain;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {

    private Long id;
    private boolean isEnabled;
    private TicketDTO ticketDomain;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;

    private String description;

    public static ReservationDTO fromDomain(ReservationDomain reservationDomain){
        return new ReservationDTO(
                reservationDomain.getId(),
                reservationDomain.isEnabled(),
                reservationDomain.getTicket() != null ? TicketDTO.fromDomain(reservationDomain.getTicket()) : null,
                reservationDomain.getDate(),
                reservationDomain.getDescription() != null ? reservationDomain.getDescription() : null
        );
    }

    public ReservationDomain toDomain(){
        return new ReservationDomain(
                getId() != null ? getId() : null,
                getDate(),
                true,
                getDescription(),
                null,
                null
        );
    }
}
