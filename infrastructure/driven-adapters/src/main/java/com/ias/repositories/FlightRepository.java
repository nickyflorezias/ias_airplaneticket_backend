package com.ias.repositories;

import com.ias.dbo.FlightDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightDBO, Long> {
    List<FlightDBO> findAllByDate(LocalDateTime date);
}
