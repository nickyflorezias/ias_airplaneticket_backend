package com.ias.repositories;

import com.ias.dbo.FlightDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightDBO, Long> {
}
