package com.example.demo.controllers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.customer;

@RestController
public class CustomerRestController {

    private List<customer> customers = new ArrayList<>(Arrays.asList(
        new customer(123,"Alex","AlexU","AlexP"),
        new customer(234,"Sebas","SebasU","SebasP"),
        new customer(456,"Carl","CarlU","CarlP"),
        new customer(678,"Jai","JaiU","JaiP")
    ));

    @GetMapping("customers")
    public List<customer> getCustomers(){
        return customers;
    }

    @GetMapping("customers/{username}")
    public customer getClient(@PathVariable String username){
        for (customer c : customers){
            if (c.getUsername().equalsIgnoreCase(username)){
                return c;
            }
        }
        return null;
    }
}
