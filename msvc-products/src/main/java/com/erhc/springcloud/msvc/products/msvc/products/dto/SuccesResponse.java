package com.erhc.springcloud.msvc.products.msvc.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccesResponse<T> {
    private String message;
    private T data;

}