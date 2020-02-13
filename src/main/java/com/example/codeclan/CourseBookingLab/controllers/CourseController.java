package com.example.codeclan.CourseBookingLab.controllers;


import com.example.codeclan.CourseBookingLab.models.Course;
import com.example.codeclan.CourseBookingLab.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;



    @GetMapping(value = "/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<Course> createNewCourse(@RequestBody Course course){
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/courses")
    public ResponseEntity<HttpStatus> deleteAllCourses(){
        courseRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/courses/{id}")
    public ResponseEntity<Optional> deleteCourseById(@PathVariable Long id){
        Optional<Course> course = courseRepository.findById(id);
        courseRepository.deleteById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PatchMapping(value = "/courses/{id}")
    public ResponseEntity<Course> editCourse(@PathVariable Long id, @RequestBody Course course){
        if (course.getId() == null){
            course.setId(id);
        } else if (course.getId() != id){
            return new ResponseEntity<>(course, HttpStatus.BAD_REQUEST);
        }
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/rating")
    public ResponseEntity<List<Course>> getCoursesForRating(@RequestParam(name="rating") int rating){
        List<Course> courses = courseRepository.findByRating(rating);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/customer/id")
    public ResponseEntity<List<Course>> getCoursesByCustomerId(@RequestParam(name="id") long id){
        List<Course> courses = courseRepository.findByBookingsCustomerId(id);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
