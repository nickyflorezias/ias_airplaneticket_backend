package com.ias.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ias.enums.HttpStatusCodeCustom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    private HttpStatusCodeCustom status;
    private String message;
}
