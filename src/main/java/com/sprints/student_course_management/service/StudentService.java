package com.sprints.student_course_management.service;

import com.sprints.student_course_management.dto.StudentDto;
import com.sprints.student_course_management.model.Student;
import com.sprints.student_course_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto createStudent(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student saved = studentRepository.save(student);
        return toDto(saved);
    }

    public List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> result = new ArrayList<>();
        for (Student s : students) {
            result.add(toDto(s));
        }
        return result;
    }

    public StudentDto findStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return toDto(student.get());
        } else
            throw new RuntimeException("Student not found with ID: " + id);
    }

    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    public StudentDto updateStudent(Long id, StudentDto dto) {
        Optional<Student> studentO = studentRepository.findById(id);
        if (studentO.isEmpty()) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        Student student = studentO.get();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        Student updated = studentRepository.save(student);
        return toDto(updated);
    }


    // So I can easily convert the student entity to dto again Including *The ID field*
    private StudentDto toDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setEmail(s.getEmail());
        dto.setAge(s.getAge());
        return dto;
    }

}
