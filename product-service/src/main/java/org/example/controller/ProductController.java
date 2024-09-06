package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.model.response.ApiResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                "A product has created successfully",
                product,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(productId, productRequest);
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                "A product has been updated successfully",
                product,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>(
                "All products have been retrieved successfully",
                products,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        ApiResponse<Product> apiResponse = new ApiResponse<>(
                "A product has been retrieved successfully",
                product,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                "A product has been deleted successfully",
                null,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
