package com.erhc.springcloud.msvc.items.services;


import com.erhc.springcloud.msvc.items.models.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder client;

    public ItemServiceWebClient(WebClient.Builder client) {
        this.client = client;
    }


    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.empty();
    }
}
