package com.erhc.springcloud.msvc.items.services;


import com.erhc.springcloud.msvc.items.dto.SuccessResponse;
import com.erhc.springcloud.msvc.items.exception.ProductNotFoundException;
import com.erhc.springcloud.msvc.items.models.Item;
import com.erhc.springcloud.msvc.items.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus; // Esta es la clase correcta

import javax.swing.text.html.Option;
import java.util.*;

//@Primary
@Service
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder client;

    public ItemServiceWebClient(WebClient.Builder client) {
        this.client = client;
    }



    @Override
    public List<Item> findAll() {
        return this.client.build()
                .get()
                .uri("http://msvc-products/api/products/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> {
                                    System.err.println("Error response: " + body);
                                    return Mono.error(new RuntimeException("Error calling msvc-products: " + body));
                                })
                )
                .bodyToFlux(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(client.build()
                .get()
                .uri("http://msvc-products/api/products/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> {
                            if (response.statusCode().equals(HttpStatus.NOT_FOUND)) {
                                return Mono.error(new ProductNotFoundException("Product with ID " + id + " not found"));
                            } else {
                                return response.bodyToMono(String.class)
                                        .flatMap(body -> Mono.error(new ResponseStatusException(
                                                response.statusCode(),
                                                "Client error: " + body
                                        )));
                            }
                        }
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new ResponseStatusException(
                                        HttpStatus.INTERNAL_SERVER_ERROR,
                                        "Server error: " + body
                                )))
                )
                .bodyToMono(new ParameterizedTypeReference<SuccessResponse<Product>>() {})
                .map(response -> new Item(response.getData(), new Random().nextInt(10) + 1))
                .block());
    }




}
