package com.consola.lis.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListAttributeDTO {


    @JsonProperty("name")
    private String name;

    @JsonProperty("list")
    private String[] list;


}
