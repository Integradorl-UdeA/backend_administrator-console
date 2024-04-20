package com.consola.lis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ListAttributeItemDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}

