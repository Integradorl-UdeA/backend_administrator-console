package com.consola.lis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T>{
    private Long size;
    private Integer pages;
    private T collection;
}
