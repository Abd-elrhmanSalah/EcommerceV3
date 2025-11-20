package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Category Controller",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE Category Details")

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Create a new category", description = "API to create a new category in the system.")
    @PostMapping("/create")
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto CategoryDto) {
        return categoryService.createCategory(CategoryDto);
    }

    @Operation(summary = "get category with item", description = "API to show categry with item in the system.")
    @GetMapping("/get-with-item/{id}")
    public CategoryResponseDto getCategoryWithItems(@PathVariable Long id) {
        return categoryService.getCategoryWithItems(id);
    }

    @Operation(summary = "show all categories", description = "API to show all categories in the system.")
    @GetMapping("/list")
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
