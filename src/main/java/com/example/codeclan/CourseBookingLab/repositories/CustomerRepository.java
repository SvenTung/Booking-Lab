package com.example.codeclan.CourseBookingLab.repositories;

import com.example.codeclan.CourseBookingLab.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
