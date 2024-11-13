package com.erhc.springcloud.msvc.items.controllers;

import com.erhc.springcloud.msvc.items.models.Item;
import com.erhc.springcloud.msvc.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/test")
//    public String test(){
//        String url = "http://localhost:9001/api/products/";  // Cambiar según el puerto
//        return restTemplate.getForObject(url, String.class);
//    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public List<ServiceInstance> testDiscovery() {
        return discoveryClient.getInstances("msvc-products");
    }

    @GetMapping("/")
    public List<Item> list() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> details(@PathVariable Long id){
        Optional<Item> itemOptional = itemService.findById(id);
        if(itemOptional.isPresent()){
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
