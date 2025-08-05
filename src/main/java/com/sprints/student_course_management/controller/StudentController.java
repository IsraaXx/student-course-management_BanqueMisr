package com.sprints.student_course_management.controller;
import com.sprints.student_course_management.dto.StudentDto;
import com.sprints.student_course_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public StudentDto createStudent(@RequestBody StudentDto studentdto) {
        return studentService.createStudent(studentdto);
    }

    @GetMapping
    public List<StudentDto> findtAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDto updateStudentById(@PathVariable Long id,@RequestBody StudentDto studentdto) {
        return studentService.updateStudent(id, studentdto);
    }


}
