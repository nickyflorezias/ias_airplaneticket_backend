package com.ias.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    private HttpStatus status;
    private String message;
}
