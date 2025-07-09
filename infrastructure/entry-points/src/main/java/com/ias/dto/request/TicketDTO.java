package com.ias.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDTO {

    @NotNull @Size(min = 3, max = 7)
    private String seat;
}
