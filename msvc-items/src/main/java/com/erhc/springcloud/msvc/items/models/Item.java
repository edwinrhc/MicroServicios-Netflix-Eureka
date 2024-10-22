package com.erhc.springcloud.msvc.items.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Product product;
    private int quantity;


    public Double getTotal(){
        return product.getPrice() * quantity;
    }
}
