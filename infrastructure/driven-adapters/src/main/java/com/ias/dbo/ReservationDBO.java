package com.ias.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDBO user;

    @OneToMany(mappedBy = "reservation")
    private List<TicketDBO> tickets;
}
