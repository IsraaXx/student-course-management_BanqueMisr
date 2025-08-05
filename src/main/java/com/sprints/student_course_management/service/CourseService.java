package com.sprints.student_course_management.service;
import com.sprints.student_course_management.dto.CourseDto;
import com.sprints.student_course_management.model.Category;
import com.sprints.student_course_management.model.Course;
import com.sprints.student_course_management.repository.CategoryRepository;
import com.sprints.student_course_management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    private CategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository , CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    public CourseDto addCourse (CourseDto dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));
            course.setCategory(category);
        }

        Course saved = courseRepository.save(course);
        return toDto(saved);
    }

    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> result = new ArrayList<>();
        for (Course c : courses) {
            result.add(toDto(c));
        }
        return result;
    }

    public CourseDto findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return toDto(course.get());
        } else
            throw new RuntimeException("Course not found with ID: " + id);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with ID: " + id);
        }
        courseRepository.deleteById(id);
    }

    public CourseDto updateCourse(Long id, CourseDto dto) {
        Optional<Course> courseO = courseRepository.findById(id);
        if (courseO.isEmpty()) {
            throw new RuntimeException("Course not found with ID: " + id);
        }
        Course course = courseO.get();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));
            course.setCategory(category);
        }

        Course updated = courseRepository.save(course);
        return toDto(updated);
    }


    // So I can easily convert the course entity to dto again Including *The ID field*
    private CourseDto toDto(Course c) {
        CourseDto dto = new CourseDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        if (c.getCategory() != null) {
            dto.setCategoryId(c.getCategory().getId());
        } else {
            dto.setCategoryId(null);
        }

        return dto;
    }
}
