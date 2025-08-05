package com.sprints.student_course_management.controller;
import com.sprints.student_course_management.dto.CourseDto;
import com.sprints.student_course_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseService.addCourse(courseDto);
    }

    @GetMapping
        public List<CourseDto> findtAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDto findCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseDto updateCourseById(@PathVariable Long id,@RequestBody CourseDto courseDto) {
        return courseService.updateCourse(id, courseDto);
    }



}
