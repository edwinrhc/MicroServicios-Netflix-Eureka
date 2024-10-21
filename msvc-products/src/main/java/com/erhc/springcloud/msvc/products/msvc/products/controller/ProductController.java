package com.erhc.springcloud.msvc.products.msvc.products.controller;

import com.erhc.springcloud.msvc.products.msvc.products.dto.SuccesResponse;
import com.erhc.springcloud.msvc.products.msvc.products.entities.Product;
import com.erhc.springcloud.msvc.products.msvc.products.exception.ProductNotFoundException;
import com.erhc.springcloud.msvc.products.msvc.products.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailsProduct(@PathVariable Long id){
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(new SuccesResponse<>("Product found successfully", product)))
                .orElseThrow( () -> new ProductNotFoundException(id) );
    }

}
