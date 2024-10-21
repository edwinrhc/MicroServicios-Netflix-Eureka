package com.erhc.springcloud.msvc.products.msvc.products.repositories;


import com.erhc.springcloud.msvc.products.msvc.products.entities.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository  extends CrudRepository<Product, Long> {


}
