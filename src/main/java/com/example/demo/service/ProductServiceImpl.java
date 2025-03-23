package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
@ConditionalOnProperty(name = "service.products", havingValue = "listService")
public class ProductServiceImpl implements ProductService {

    List<Product> productsList = new ArrayList<>(Arrays.asList(
        new Product(1, "Laptop", 799.99, 10),
        new Product(2, "Smartphone", 399.99, 15),
        new Product(3, "Tablet", 299.99, 13),
        new Product(4, "Watch", 199.99, 20)
    ));

    @Override
    public List<Product> getProducts(){
        return productsList;
    }

    @Override
    public Product getProductById(int id) {
        for(Product p: productsList){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        for(Product p: productsList){
            if(p.getId()==product.getId()){
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setStock(product.getStock());
                return true;
            }
        }
        return false;
    }

    @Override
    public Product createProduct(Product product) {
        productsList.add(product);
        return product;
    }

    @Override
    public Product deleteProduct(int id) {
        for(Product p:productsList){
            if(p.getId()==id){
                productsList.remove(p);

                return p;
            }
        }
        return null;
    }

    @Override
    public boolean patchProduct(Product product) {
        for(Product p:productsList){
            if(p.getId()==product.getId()){
                if(product.getName()!=null){
                    p.setName(product.getName());
                }
                if(product.getPrice()!=null){
                    p.setPrice(product.getPrice());
                }
                if(product.getStock()!=null){
                    p.setStock(product.getStock());
                }
                return true;
            }
        }
        return false;
    }
    
}
