package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	private final WebClient webClient;

    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        // Make a POST request and block to get the response synchronously
        return webClient.post()
                .uri("/api/products")
                .bodyValue(productRequest)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block(); // Block to get the response synchronously
    }

    public ProductResponse getProductById(Long id) {
        // Make a GET request and block to get the response synchronously
        return webClient.get()
                .uri("/api/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block(); // Block to get the response synchronously
    }

    public ProductResponse updateProductById(ProductRequest productRequest, Long id) {
        // Make a PUT request and block to get the response synchronously
        return webClient.put()
                .uri("/api/products/{id}", id)
                .bodyValue(productRequest)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block(); // Block to get the response synchronously
    }

    public boolean deleteProductById(Long id) {
        // Make a DELETE request and block to get the response synchronously
        return webClient.delete()
                .uri("/api/products/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block(); // Block to get the response synchronously
    }

    public List<ProductResponse> getAllProducts() {
        // Make a GET request and block to get the list response synchronously
        return webClient.get()
                .uri("/api/products")
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block(); // Block to get the response synchronously
    }
}
