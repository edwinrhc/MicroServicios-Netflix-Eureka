package com.erhc.springcloud.msvc.items.clients;

import com.erhc.springcloud.msvc.items.configuration.FeignConfig;
import com.erhc.springcloud.msvc.items.dto.SuccessResponse;
import com.erhc.springcloud.msvc.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8001/api/products",name = "msvc-products", configuration = FeignConfig.class)
public interface ProductFeignClient {

    @GetMapping("/")
    List<Product> findAll();

    @GetMapping("/{id}")
    SuccessResponse<Product> details(@PathVariable Long id);



}
