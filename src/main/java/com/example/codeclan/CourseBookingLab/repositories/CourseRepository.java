package com.example.codeclan.CourseBookingLab.repositories;

import com.example.codeclan.CourseBookingLab.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
}
