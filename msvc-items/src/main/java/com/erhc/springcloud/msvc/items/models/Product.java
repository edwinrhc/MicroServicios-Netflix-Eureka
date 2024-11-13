package com.erhc.springcloud.msvc.items.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double price = 0.0;
    private LocalDate createAt;
    private int port;


}
