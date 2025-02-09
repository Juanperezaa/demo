package com.example.demo.controllers;

import java.util.Arrays;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

   
    //@RequestMapping(method = RequestMethod.GET)src/main/java/com/example/demo/controllers/CustomerRestController.java
    @GetMapping
    public ResponseEntity<List<customer>> getCustomers(){
        return ResponseEntity.ok(customers);
    }

    //RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @GetMapping("/{username}")
    public ResponseEntity<?> getClient(@PathVariable String username){
        for (customer c : customers){
            if (c.getUsername().equalsIgnoreCase(username)){
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with username" + username);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity <?> postCustomer(@RequestBody customer customer){
        customers.add(customer);

        URI locationUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{username}")
                    .buildAndExpand(customer.getUsername())
                    .toUri();

        return ResponseEntity.created(locationUri).build();
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCustomer(@RequestBody customer customer){
        for(customer c: customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCustomer(@PathVariable int id){
        for(customer c: customers){
            if(c.getID()==id){
                customers.remove(c);

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    //@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<?> patchCustomer(@RequestBody customer customer){
        for(customer c: customers){
            if(c.getID()==customer.getID()){
                if(customer.getName()!=null){
                    c.setName(customer.getName());
                }
                if(customer.getUsername()!=null){
                    c.setUsername(customer.getUsername());
                }
                if(customer.getPassword()!=null){
                    c.setPassword(customer.getPassword());
                }
                
                return ResponseEntity.ok("Customer modified with id "+customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with id "+ customer.getID());
    }


}
