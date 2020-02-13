package com.example.codeclan.CourseBookingLab.components;

import com.example.codeclan.CourseBookingLab.models.Booking;
import com.example.codeclan.CourseBookingLab.models.Course;
import com.example.codeclan.CourseBookingLab.models.Customer;
import com.example.codeclan.CourseBookingLab.repositories.BookingRepository;
import com.example.codeclan.CourseBookingLab.repositories.CourseRepository;
import com.example.codeclan.CourseBookingLab.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CustomerRepository customerRepository;


    public DataLoader() {
    }

    public void run(ApplicationArguments args){
        Course course1 = new Course("Knitting", "Madrid", 4);
        courseRepository.save(course1);

        Course course2 = new Course("Needle Point", "Cumbernauld", 3);
        courseRepository.save(course2);

        Customer customer1 = new Customer("Stephen Strange", "New York", 39);
        customerRepository.save(customer1);

        Customer customer2 = new Customer("Tony Stark", "LA", 42);
        customerRepository.save(customer2);

        Booking booking1 = new Booking("12/4/2020", customer1, course1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking("18/5/2020", customer2, course2);
        bookingRepository.save(booking2);

        customer1.addBooking(booking1);
        customerRepository.save(customer1);

        customer2.addBooking(booking2);
        customerRepository.save(customer2);

        course1.addBooking(booking1);
        courseRepository.save(course1);

        course2.addBooking(booking2);
        courseRepository.save(course2);
    }
}
