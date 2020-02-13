package com.example.codeclan.CourseBookingLab.controllers;

import com.example.codeclan.CourseBookingLab.models.Course;
import com.example.codeclan.CourseBookingLab.models.Customer;
import com.example.codeclan.CourseBookingLab.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomers(){
        customerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Optional> deleteCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        customerRepository.deleteById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PatchMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable Long id, @RequestBody Customer customer){
        if (customer.getId() == null){
            customer.setId(id);
        } else if (customer.getId() != id){
            return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        }
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/course/id")
    public ResponseEntity<List<Customer>> getCustomersByCourseId(@RequestParam(name="id") Long id){
        List<Customer> customers = customerRepository.findByBookingsCourseId(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/town/{town}/course/id")
    public ResponseEntity<List<Customer>> getCustomersByTownAndCourse(@RequestParam(name="id") Long id, @PathVariable String town){
        List<Customer> customers = customerRepository.findByTownAndBookingsCourseId(town, id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/age/{age}/town/{town}/course/id")
    public ResponseEntity<List<Customer>> getCustomersByAgeAndTownAndCourse(@RequestParam(name="id") Long id, @PathVariable int age, @PathVariable String town){
        List<Customer> customers = customerRepository.findByAgeAndTownAndBookingsCourseId(age, town, id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
