package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Lazy
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "jsonService")
public class ProductServiceJSONImpl implements ProductService {

   

    @Override
    public List<Product> getProducts() {
        List<Product> products;
        try {
            products = new ObjectMapper()
                    .readValue(this.getClass()
                    .getResourceAsStream("/products/products.json"),
                    new TypeReference<List<Product>>(){});

            return products;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(int id) {
        List<Product> products;
        try {
            products = new ObjectMapper()
                    .readValue(this.getClass()
                    .getResourceAsStream("/products/products.json"),
                    new TypeReference<List<Product>>(){});
            
                    return products.stream()
                    .filter(product -> product.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        List<Product> products;
        try {
            products = new ObjectMapper()
                        .readValue(this.getClass()
                        .getResourceAsStream("/products/products.json"),
                        new TypeReference<List<Product>>(){});
                        for(Product p: products);

            for(Product p: products){
                if(p.getId()==product.getId()){
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                    return true;
                }
            }
            return false;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       
    }

    @Override
    public Product createProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public Product deleteProduct(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public boolean patchProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchProduct'");
    }


}
