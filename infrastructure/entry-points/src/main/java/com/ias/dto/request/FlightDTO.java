package com.ias.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ias.enums.AirlineName;
import com.ias.FlightDomain;
import com.ias.enums.FlightType;
import com.ias.enums.PlaneName;
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

    @NotNull(message = "Origin city can't be null")
    private String originCity;

    @NotNull(message = "Destiny city can't be null")
    private String destinyCity;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;

    @NotNull(message = "Plane name can't be null")
    private PlaneName planeName;

    @NotNull(message = "Flight type can't be null")
    private FlightType flightType;

    @NotNull(message = "Airline name can't be null")
    private AirlineName airlineName;

    @NotNull(message = "Cant seats can't be null")
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
                flightDomain.getType(),
                flightDomain.getAirlineName(),
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
                getFlightType(),
                getAirlineName(),
                getCantSeats(),
                isFull,
                null
        );
    }
}
