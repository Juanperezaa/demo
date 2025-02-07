package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductsServiceImpl productsServiceImpl = new ProductsServiceImpl();

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products= productsServiceImpl.getProducts();

        return ResponseEntity.ok(products);
    }

}
