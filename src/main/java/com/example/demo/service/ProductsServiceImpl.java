package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.model.Product;

public class ProductsServiceImpl {

    List<Product> productsList = new ArrayList<>(Arrays.asList(
        new Product(1, "Laptop", 799.99, 10),
        new Product(2, "Smartphone", 399.99, 15),
        new Product(3, "Tablet", 299.99, 13),
        new Product(4, "Watch", 199.99, 20)
    ));

    public List<Product> getProducts(){
        return productsList;
    }
}
