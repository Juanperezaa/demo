package com.example.demo.controllers;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.model.customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private List<customer> customers = new ArrayList<>(Arrays.asList(
        new customer(123,"Alex","AlexU","AlexP"),
        new customer(234,"Sebas","SebasU","SebasP"),
        new customer(456,"Carl","CarlU","CarlP"),
        new customer(678,"Jai","JaiU","JaiP")
    ));

    //@RequestMapping(method = RequestMethod.GET)
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
    @PostMapping()
    public ResponseEntity <?> postCustomer(@RequestBody customer customer){
        customers.add(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created succesfully with id "+customer.getID());
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCustomer(@RequestBody customer customer){
        for(customer c: customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.ok("Customer updated succesfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID "+ customer.getID());
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteCustomer(@PathVariable int id){
        for(customer c: customers){
            if(c.getID()==id){
                customers.remove(c);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer deleted with ID "+ c.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with id "+ id);
    }

    //@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping()
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
