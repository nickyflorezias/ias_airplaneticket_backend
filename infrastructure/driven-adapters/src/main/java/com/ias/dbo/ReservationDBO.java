package com.ias.dbo;

import com.ias.FlightDomain;
import com.ias.ReservationDomain;
import com.ias.TicketDomain;
import com.ias.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "reservation_TABLE")
public class ReservationDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private boolean isEnabled;

    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDBO user;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private TicketDBO ticket;

    public static ReservationDBO fromDomain(ReservationDomain domain) {
        if (domain == null) return null;

        ReservationDBO reservationDBO = new ReservationDBO();
        reservationDBO.setId(domain.getId());
        reservationDBO.setDate(domain.getDate());
        reservationDBO.setEnabled(domain.isEnabled());
        reservationDBO.setDescription(domain.getDescription());

        if (domain.getUserDomain() != null) {
            reservationDBO.setUser(new UserDBO(
                    domain.getUserDomain().getId(),
                    domain.getUserDomain().getUsername(),
                    domain.getUserDomain().getEmail(),
                    domain.getUserDomain().getPassword(),
                    domain.getUserDomain().getTickets() != null ? domain.getUserDomain().getTickets().stream()
                            .map(ticketDomain -> new TicketDBO(
                                    ticketDomain.getId(),
                                    ticketDomain.getSeat(),
                                    null,
                                    null,
                                    null
                            )).toList() : null,
                    null
            ));
        }

        if(domain.getTicket() != null){
            reservationDBO.setTicket(
                    new TicketDBO(
                            domain.getTicket().getId(),
                            domain.getTicket().getSeat(),
                            domain.getTicket().getFlight() != null ? new FlightDBO(
                                    domain.getTicket().getFlight().getId(),
                                    domain.getTicket().getFlight().getName(),
                                    domain.getTicket().getFlight().getOriginCity(),
                                    domain.getTicket().getFlight().getDestinyCity(),
                                    domain.getTicket().getFlight().getDate(),
                                    domain.getTicket().getFlight().getPlaneName(),
                                    domain.getTicket().getFlight().getCantSeats(),
                                    domain.getTicket().getFlight().isFull(),
                                    null
                            ) : null,
                            null,
                            null
                    ));
        }else{
            reservationDBO.setTicket(null);
        }

        return reservationDBO;
    }

    public ReservationDomain toDomain() {
        return new ReservationDomain(
                this.id,
                this.date,
                this.isEnabled,
                this.description,
                this.user != null ? new UserDomain(
                        this.user.getId(),
                        this.user.getUsername(),
                        this.user.getEmail(),
                        this.user.getPassword(),
                        this.user.getTickets() != null ? this.user.getTickets().stream()
                                .map(ticketDBO -> new TicketDomain(
                                        ticketDBO.getId(),
                                        ticketDBO.getSeat(),
                                        ticketDBO.getFlight() != null ? new FlightDomain(
                                                ticketDBO.getFlight().getId(),
                                                ticketDBO.getFlight().getName(),
                                                ticketDBO.getFlight().getOriginCity(),
                                                ticketDBO.getFlight().getDestinyCity(),
                                                ticketDBO.getFlight().getDate(),
                                                ticketDBO.getFlight().getPlaneName(),
                                                ticketDBO.getFlight().getCantSeats(),
                                                ticketDBO.getFlight().isFull(),
                                                null
                                        ) : null,
                                        null,
                                        null
                                )).toList() : null,
                        null
                ) : null,
                this.ticket != null ? new TicketDomain(
                        this.ticket.getId(),
                        this.ticket.getSeat(),
                        this.ticket.getFlight() != null ? new FlightDomain(
                                this.ticket.getFlight().getId(),
                                this.ticket.getFlight().getName(),
                                this.ticket.getFlight().getOriginCity(),
                                this.ticket.getFlight().getDestinyCity(),
                                this.ticket.getFlight().getDate(),
                                this.ticket.getFlight().getPlaneName(),
                                this.ticket.getFlight().getCantSeats(),
                                this.ticket.getFlight().isFull(),
                                null
                        ) : null,
                        null,
                        null
                ) : null
        );
    }
}
