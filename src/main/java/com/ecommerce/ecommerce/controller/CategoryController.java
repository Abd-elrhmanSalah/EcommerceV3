package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto CategoryDto) {
        return categoryService.createCategory(CategoryDto);
    }


    @GetMapping("/get-with-item/{id}")
    public CategoryResponseDto getCategoryWithItems(@PathVariable Long id) {
        return categoryService.getCategoryWithItems(id);
    }


    @GetMapping("/list")
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
