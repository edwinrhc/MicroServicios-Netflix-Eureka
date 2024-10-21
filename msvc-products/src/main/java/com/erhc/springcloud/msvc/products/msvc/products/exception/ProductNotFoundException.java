package com.erhc.springcloud.msvc.products.msvc.products.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product not found with id: " + id);
    }
}
