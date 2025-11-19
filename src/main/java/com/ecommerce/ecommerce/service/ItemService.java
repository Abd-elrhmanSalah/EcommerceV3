package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryRepository categoryRepository;


    private ItemResponseDto convertToDto(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getPrice(),
                item.getImagePath(),
                item.isBooked(),
                item.getCategory() != null ? item.getCategory().getTitle() : null
        );
    }


    public ItemResponseDto createItem(ItemRequestDto itemDto) {
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Item item = new Item();
        item.setTitle(itemDto.getTitle());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setImagePath(itemDto.getImagePath());
        item.setCategory(category);
        item.setBooked(false);
        Item itemSave = itemRepository.save(item);
        return convertToDto(itemSave);
    }


    public ItemResponseDto updateItem(Long id, ItemRequestDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        item.setTitle(itemDto.getTitle());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setImagePath(itemDto.getImagePath());
        item.setCategory(category);
        Item itemSave = itemRepository.save(item);
        return convertToDto(itemSave);
    }


    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));
        itemRepository.delete(item);
    }


    public List<ItemResponseDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<ItemResponseDto> getAllBookedItems() {
        List<Item> items = itemRepository.findByBooked(true);
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<ItemResponseDto> getAllAvailableItems() {
        List<Item> items = itemRepository.findByBooked(false);
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<ItemResponseDto> getItemsByPriceRange(double minPrice, double maxPrice) {
        List<Item> items = itemRepository.findByPriceBetween(minPrice, maxPrice);
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public ItemResponseDto updateItemBooked(Long id, boolean booked) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setBooked(booked);
        Item itemSave = itemRepository.save(item);
        return convertToDto(itemSave);
    }


    public ItemResponseDto getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));
        return convertToDto(item);
    }


    public List<ItemResponseDto> getItemsByCategory(Long categoryId) {
        List<Item> items = itemRepository.findByCategoryId(categoryId);
        return items.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
