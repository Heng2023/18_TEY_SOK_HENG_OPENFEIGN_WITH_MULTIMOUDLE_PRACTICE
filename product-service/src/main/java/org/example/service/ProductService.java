package org.example.service;

import org.example.model.Product;
import org.example.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(Long productId, ProductRequest productRequest);
    List<Product> getAllProducts();
    void deleteProduct(Long productId);
    Product getProduct(Long productId);
}
