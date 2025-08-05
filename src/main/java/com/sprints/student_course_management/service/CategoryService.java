package com.sprints.student_course_management.service;


import com.sprints.student_course_management.dto.CategoryDto;
import com.sprints.student_course_management.model.Category;
import com.sprints.student_course_management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto CreateCategory(CategoryDto cate){
        Category category = new Category();
        category.setName(cate.getName());
        Category newCate = categoryRepository.save(category);
        return dto(newCate);
    }
    public List<CategoryDto>findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> catedto = new ArrayList<>() ;
        for(Category c : categories){
            catedto.add(dto(c));
        }
        return catedto;
    }

    public CategoryDto findCategoryById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return dto(category.get());
        }
        else
            throw new RuntimeException("Category not found with ID: " + id);
    }

    public void deleteCategoryById(Long id){
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryDto updateCategory(Long id , CategoryDto catedto){
        Optional<Category> categoryO = categoryRepository.findById(id);
        if (categoryO.isEmpty()) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        Category category =  categoryO.get();
        category.setName(catedto.getName());

        Category updated = categoryRepository.save(category);

        return dto(updated);
    }


    private CategoryDto dto(Category cate){
        CategoryDto cateDto =  new CategoryDto();
        cateDto.setId(cate.getId());
        cateDto.setName(cate.getName());
        return cateDto;
    }

}
