package com.ias.repositories;

import com.ias.dbo.ReservationDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationDBO, Long> {
}
