package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {

    public List<Product> getProducts();
    public Product getProductById(int id);
}
