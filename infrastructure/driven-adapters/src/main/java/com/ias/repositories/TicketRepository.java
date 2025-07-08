package com.ias.repositories;

import com.ias.dbo.TicketDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketDBO, Long> {
}
