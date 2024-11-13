package com.erhc.springcloud.msvc.items.clients;

import com.erhc.springcloud.msvc.items.configuration.FeignConfig;
import com.erhc.springcloud.msvc.items.dto.SuccessResponse;
import com.erhc.springcloud.msvc.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@FeignClient(url = "http://localhost:8001/api/products",name = "msvc-products", configuration = FeignConfig.class)
@FeignClient(name = "msvc-products", configuration = FeignConfig.class)
public interface ProductFeignClient {

    @GetMapping("/api/products/")
    List<Product> findAll();

    @GetMapping("/api/products/{id}")
    SuccessResponse<Product> details(@PathVariable Long id);



}
