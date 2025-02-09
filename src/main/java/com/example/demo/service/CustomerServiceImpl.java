package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.customer;

public class CustomerServiceImpl {

     private List<customer> customers = new ArrayList<>(Arrays.asList(
        new customer(123,"Alex","AlexU","AlexP"),
        new customer(234,"Sebas","SebasU","SebasP"),
        new customer(456,"Carl","CarlU","CarlP"),
        new customer(678,"Jai","JaiU","JaiP")
    ));

    public ResponseEntity<?> getClient(){
        
    }
}
