package com.sprints.student_course_management.controller;
import com.sprints.student_course_management.dto.CategoryDto;
import com.sprints.student_course_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryservice){
        this.categoryService = categoryservice;
    }

    @PostMapping("/create")
    public CategoryDto createCategory(@RequestBody CategoryDto categorydto){
        return categoryService.CreateCategory(categorydto);
    }
    @GetMapping
    public List<CategoryDto> findAllCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDto updateStudentById(@PathVariable Long id,@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

}
