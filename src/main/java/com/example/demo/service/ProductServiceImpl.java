package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
@ConditionalOnProperty(name = "service.products", havingValue = "listService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    
   /* List<Product> productsList = new ArrayList<>(Arrays.asList(
        new Product(1, "Laptop", 799.99, 10),
        new Product(2, "Smartphone", 399.99, 15),
        new Product(3, "Tablet", 299.99, 13),
        new Product(4, "Watch", 199.99, 20)
    ));*/ 

    @Override
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(new Product());
    }

    @Override
    public boolean updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            return false;
        }
        productRepository.save(product);
        return true;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(int id) {
        return productRepository.findById(id)
        .map(product -> {
            productRepository.delete(product);
            return product;
        })
        .orElse(null);
    }

    @Override
    public boolean patchProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        if (optionalProduct.isEmpty()) {
            return false;
        }

        Product existingProduct = optionalProduct.get();

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getStock() != null) {
            existingProduct.setStock(product.getStock());
        }

        productRepository.save(existingProduct); // Se guarda el producto actualizado

        return true;
    }
    
}
