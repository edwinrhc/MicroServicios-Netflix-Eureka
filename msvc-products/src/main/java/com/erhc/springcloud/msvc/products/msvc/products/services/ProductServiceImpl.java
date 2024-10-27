package com.erhc.springcloud.msvc.products.msvc.products.services;

import com.erhc.springcloud.msvc.products.msvc.products.entities.Product;
import com.erhc.springcloud.msvc.products.msvc.products.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements  ProductService{

   final  private ProductRepository productRepository;
   final private Environment environment;
    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) productRepository.findAll()).stream().map(
                product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                }).collect(Collectors.toList());// hacemos un iterable
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(
                product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                }
        );
    }
}
