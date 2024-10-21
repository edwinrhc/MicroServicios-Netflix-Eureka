package com.erhc.springcloud.msvc.products.msvc.products.services;

import com.erhc.springcloud.msvc.products.msvc.products.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);

}
