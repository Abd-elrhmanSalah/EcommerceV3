package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CategoryRequestDto;
import com.ecommerce.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ItemService itemService;


    private CategoryResponseDto convertToDto(Category category, List<ItemResponseDto> items) {
        return new CategoryResponseDto(
                category.getId(),
                category.getTitle(),
                items
        );
    }

    public CategoryResponseDto createCategory(CategoryRequestDto categoryDto) {
        if (categoryRepository.existsByTitle(categoryDto.getTitle())) {
            throw new RuntimeException("Category already exists");
        }
        Category map = ObjectMapperUtils.map(categoryDto, Category.class);

        Category categorySave = categoryRepository.save(map);
        return convertToDto(categorySave, null);
    }


    public CategoryResponseDto getCategoryWithItems(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
        List<ItemResponseDto> items = itemService.getItemsByCategory(id);
        return convertToDto(category, items);
    }


    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> convertToDto(category, null)).toList();
    }

}
