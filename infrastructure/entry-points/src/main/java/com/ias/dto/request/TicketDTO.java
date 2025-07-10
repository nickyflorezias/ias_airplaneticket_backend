package com.ias.dto.request;

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
    @NotNull @Size(min = 3, max = 7)
    private String seat;

    public static TicketDTO fromDomain(TicketDomain ticketDomain){
        return new TicketDTO(
                ticketDomain.getId(),
                ticketDomain.getFlight() != null ? FlightDTO.fromDomain(ticketDomain.getFlight()) : null,
                ticketDomain.getSeat()
        );
    }

    public TicketDomain toDomain(){
        return new TicketDomain(
                null,
                getSeat(),
                null,
                null,
                null
        );
    }
}
