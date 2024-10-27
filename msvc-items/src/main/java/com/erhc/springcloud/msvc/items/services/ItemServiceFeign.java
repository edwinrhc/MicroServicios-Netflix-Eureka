package com.erhc.springcloud.msvc.items.services;


import com.erhc.springcloud.msvc.items.clients.ProductFeignClient;
import com.erhc.springcloud.msvc.items.dto.SuccessResponse;
import com.erhc.springcloud.msvc.items.models.Item;
import com.erhc.springcloud.msvc.items.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductFeignClient client;



    @Override
    public List<Item> findAll() {
        return client.findAll().stream().map(product ->{
            Random random = new Random();
            return new Item(product, random.nextInt(10) + 1);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        SuccessResponse<Product> response = client.details(id);
        Product product = response.getData(); // Extrae el producto desde `data`.
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(new Item(product, new Random().nextInt(10) + 1));

//       Product product = client.details(id);
//        if(product == null){
//            return Optional.empty();
//        }
//        return Optional.of(new Item(product,new Random().nextInt(10) + 1));
    }
}
