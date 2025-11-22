package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ItemRepository;
import com.ecommerce.ecommerce.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;


    public ItemResponseDto createItem(ItemRequestDto itemDto) {
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        Item itemMapped = ObjectMapperUtils.map(itemDto, Item.class);
        itemMapped.setCategory(category);

        Item saved = itemRepository.save(itemMapped);
        return ObjectMapperUtils.map(saved, ItemResponseDto.class);
    }

    public ItemResponseDto updateItem(Long id, ItemRequestDto itemDto) {
        itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));

        categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        Item map = ObjectMapperUtils.map(itemDto, Item.class);
        map.setId(id);


        Item saved = itemRepository.save(map);
        return ObjectMapperUtils.map(saved, ItemResponseDto.class);
    }


    public void deleteItem(Long id) {
        Item existingItem = itemRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));
        existingItem.setIsDeleted(true);
        itemRepository.save(existingItem);
    }


    public List<ItemResponseDto> getAllItems() {
        return ObjectMapperUtils.mapAll(itemRepository.findAll(), ItemResponseDto.class);
    }


    public List<ItemResponseDto> getAllBookedItems() {
        List<Item> items = itemRepository.findAllByBooked(true);
        return ObjectMapperUtils.mapAll(items, ItemResponseDto.class);
    }


    public List<ItemResponseDto> getAllAvailableItems() {
        List<Item> items = itemRepository.findAllByBooked(false);
        return ObjectMapperUtils.mapAll(items, ItemResponseDto.class);
    }


    public List<ItemResponseDto> getItemsByPriceRange(double minPrice, double maxPrice) {
        List<Item> items = itemRepository.findByPriceBetween(minPrice, maxPrice);
        return ObjectMapperUtils.mapAll(items, ItemResponseDto.class);
    }


    public ItemResponseDto updateItemBooked(Long id, boolean booked) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));
        item.setBooked(booked);
        return ObjectMapperUtils.map(item, ItemResponseDto.class);
    }


    public ItemResponseDto getItemById(Long id) {
        boolean existsItemById = itemRepository.existsById(id);
        if (!existsItemById) {
            throw new RuntimeException("Item Not Find!");
        }
        Item item = itemRepository.findById(id).get();
        return ObjectMapperUtils.map(item, ItemResponseDto.class);
    }


    public List<ItemResponseDto> getItemsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        List<Item> items = itemRepository.findAllByCategory(category);
        return ObjectMapperUtils.mapAll(items, ItemResponseDto.class);
    }
}