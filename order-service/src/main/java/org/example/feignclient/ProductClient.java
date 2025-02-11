package org.example.feignclient;

import org.example.model.response.ApiResponse;
import org.example.model.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:9090/api/v1/products")
public interface ProductClient {
    @GetMapping("/{productId}")
    ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable Long productId);
}
