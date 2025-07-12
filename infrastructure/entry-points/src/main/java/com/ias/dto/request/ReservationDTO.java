package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ias.ReservationDomain;
import com.ias.enums.ReservationStatus;
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
    private ReservationStatus status;
    private TicketDTO ticketDomain;

    @NotNull(message = "Date can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;

    private String description;

    public static ReservationDTO fromDomain(ReservationDomain reservationDomain){
        return new ReservationDTO(
                reservationDomain.getId(),
                reservationDomain.getStatus(),
                reservationDomain.getTicket() != null ? TicketDTO.fromDomain(reservationDomain.getTicket()) : null,
                reservationDomain.getDate(),
                reservationDomain.getDescription() != null ? reservationDomain.getDescription() : null
        );
    }

    public ReservationDomain toDomain(){
        return new ReservationDomain(
                getId() != null ? getId() : null,
                getDate(),
                ReservationStatus.UNPAID,
                getDescription(),
                null,
                null
        );
    }
}
