package com.ias.dto.request;

import com.ias.enums.SeatClass;
import com.ias.TicketDomain;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDTO {

    private Long id;
    private FlightDTO flightDomain;
    @NotNull(message = "seat can't be null") @Size(min = 3, max = 7, message = "Seat should be longer than 2 but less than 8")
    private String seat;

    @NotNull(message = "SeatClass can't be null")
    private SeatClass seatClass;

    public static TicketDTO fromDomain(TicketDomain ticketDomain){
        return new TicketDTO(
                ticketDomain.getId(),
                ticketDomain.getFlight() != null ? FlightDTO.fromDomain(ticketDomain.getFlight()) : null,
                ticketDomain.getSeat(),
                ticketDomain.getSeatClass()
        );
    }

    public TicketDomain toDomain(){
        return new TicketDomain(
                null,
                getSeat(),
                getSeatClass(),
                null,
                null,
                null
        );
    }
}
