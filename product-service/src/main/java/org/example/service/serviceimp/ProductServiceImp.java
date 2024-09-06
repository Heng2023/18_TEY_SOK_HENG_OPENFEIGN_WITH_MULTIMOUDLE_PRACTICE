package org.example.service.serviceimp;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequest productRequest) {
        Product product = getProduct(productId);
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found"));
    }
}
