package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.SystemUser;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto createCategory(CategoryRequestDto categoryDto) {
        if (categoryRepository.existsByTitle(categoryDto.getTitle())) {
            throw new RuntimeException("Category already exists");
        }

        Category map = ObjectMapperUtils.map(categoryDto, Category.class);
        Category categorySave = categoryRepository.save(map);
        return ObjectMapperUtils.map(categorySave, CategoryResponseDto.class);
    }


    public CategoryResponseDto getCategoryWithItems(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
        return ObjectMapperUtils.map(category, CategoryResponseDto.class);
    }


    public List<CategoryResponseDto> getAllCategories() {
        return ObjectMapperUtils.mapAll(categoryRepository.findAll(), CategoryResponseDto.class);
    }


    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
        existingCategory.setTitle(categoryDto.getTitle());
        if (categoryRepository.existsByTitle(categoryDto.getTitle())) {
            throw new RuntimeException("Category name is already exist");
        }
        Category saved = categoryRepository.save(existingCategory);
        return ObjectMapperUtils.map(saved, CategoryResponseDto.class);
    }


    public void delete(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
        existingCategory.setIsDeleted(true);
        categoryRepository.save(existingCategory);
    }

}
