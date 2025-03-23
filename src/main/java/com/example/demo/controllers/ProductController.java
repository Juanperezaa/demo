package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    @Lazy
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products= productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById (@PathVariable int id){
        Product product= productService.getProductById(id);
        if(product==null){
              return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Product not found with ID" + id);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<?> putProduct(@RequestBody Product product){
        if(!productService.updateProduct(product)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Product product){
        Product createdProduct=productService.createProduct(product);

        URI locationUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdProduct.getId()).toUri();
        
        return ResponseEntity.created(locationUri).build();
    }
}
