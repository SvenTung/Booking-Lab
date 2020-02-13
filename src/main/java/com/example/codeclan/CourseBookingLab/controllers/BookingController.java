package com.example.codeclan.CourseBookingLab.controllers;

import com.example.codeclan.CourseBookingLab.models.Booking;
import com.example.codeclan.CourseBookingLab.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;


    @GetMapping(value = "/bookings")
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity getBookingById(@PathVariable Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping(value = "/bookings")
    public ResponseEntity<Booking> createNewBooking(@RequestBody Booking booking){
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/bookings")
    public ResponseEntity<HttpStatus> deleteAllBookings(){
        bookingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity<Optional> deleteBookingById(@PathVariable Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        bookingRepository.deleteById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PatchMapping(value = "/bookings/{id}")
    public ResponseEntity<Booking> editBooking(@PathVariable Long id, @RequestBody Booking booking){
        if (booking.getId() == null){
            booking.setId(id);
        } else if (booking.getId() != id){
            return new ResponseEntity<>(booking, HttpStatus.BAD_REQUEST);
        }
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/date")
    public ResponseEntity<List<Booking>> getBookingsForDate(@RequestParam(name="date") String date){
        List<Booking> bookings = bookingRepository.findByDateIgnoreCase(date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}


