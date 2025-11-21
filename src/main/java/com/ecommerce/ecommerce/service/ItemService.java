package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
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
        boolean isCategoryExist = categoryRepository.existsById(itemDto.getCategoryId());
        if (!isCategoryExist) {
            throw new RuntimeException("Category Not Found!");
        }
        Item mappedItem = ObjectMapperUtils.map(itemDto, Item.class);
        Item item = itemRepository.save(mappedItem);
        return ObjectMapperUtils.map(item, ItemResponseDto.class);
    }

    public ItemResponseDto updateItem(Long id, ItemRequestDto itemDto) {
        boolean existsItemById = itemRepository.existsById(id);
        if (!existsItemById) {
            throw new RuntimeException("Item Not Found!");
        }
        boolean existsCategoryById = categoryRepository.existsById(itemDto.getCategoryId());
        if (!existsCategoryById) {
            throw new RuntimeException("Category Not Found!");
        }

        Item mappedItem = ObjectMapperUtils.map(itemDto, Item.class);
        Item item = itemRepository.save(mappedItem);
        return ObjectMapperUtils.map(item, ItemResponseDto.class);
    }


    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Not Found!"));
        itemRepository.delete(item);
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


    public void updateItemBooked(Long id, boolean booked) {

        boolean existsBookedById = itemRepository.existsById(id);
        if (!existsBookedById) {
            throw new RuntimeException("Item Not Found!");
        }
        Item item = itemRepository.findById(id).get();
        item.setBooked(booked);
        itemRepository.save(item);

    }


    public ItemResponseDto getItemById(Long id) {
        boolean existsItemById = itemRepository.existsById(id);
        if (!existsItemById) {
            throw new RuntimeException("Item Not Find!");
        }
        Item item = itemRepository.findById(id).get();
        return ObjectMapperUtils.map(item, ItemResponseDto.class);
    }


    public ItemResponseDto getItemsByCategory(Long categoryId) {
        boolean existsItemByCategoryId = itemRepository.existsById(categoryId);
        if (!existsItemByCategoryId) {
            throw new RuntimeException("Category Not Found!");
        }
        return ObjectMapperUtils.map(itemRepository.findById(categoryId), ItemResponseDto.class);
    }
}
