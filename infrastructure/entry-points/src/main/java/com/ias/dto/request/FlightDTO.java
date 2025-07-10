package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ias.FlightDomain;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDTO {

    private Long id;
    private boolean isFull;
    @NotNull @Size(min = 1, max = 30)
    private String name;
    private String originCity;
    private String destinyCity;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;
    private String planeName;
    private int cantSeats;

    public static FlightDTO fromDomain(FlightDomain flightDomain){
        return new FlightDTO(
                flightDomain.getId(),
                flightDomain.isFull(),
                flightDomain.getName(),
                flightDomain.getOriginCity(),
                flightDomain.getDestinyCity(),
                flightDomain.getDate(),
                flightDomain.getPlaneName(),
                flightDomain.getCantSeats()
        );
    }

    public FlightDomain toDomain(){
        return new FlightDomain(
                getId(),
                getName(),
                getOriginCity(),
                getDestinyCity(),
                getDate(),
                getPlaneName(),
                getCantSeats(),
                isFull,
                null
        );
    }
}
