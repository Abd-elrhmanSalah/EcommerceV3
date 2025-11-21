package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "create new item", description = "API to create new item")
    @PostMapping("/create")
    public ItemResponseDto createItem(@RequestBody ItemRequestDto itemDto) {
        return itemService.createItem(itemDto);
    }


    @Operation(summary = "update item by id", description = "API to update item in system")
    @PutMapping("/update/{id}")
    public ItemResponseDto updateItem(@PathVariable Long id, @RequestBody ItemRequestDto itemDto) {
        return itemService.updateItem(id, itemDto);
    }


    @Operation(summary = "delete item by id", description = "API to delete item in the system by id")
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }


    @Operation(summary = "show all items", description = "API to show all items in system.")
    @GetMapping("/List")
    public List<ItemResponseDto> getAllItems() {
        return itemService.getAllItems();
    }


    @Operation(summary = "booked item by id", description = "API to booked item in the system by id")
    @GetMapping("/booked")
    public List<ItemResponseDto> getAllBookedItems() {
        return itemService.getAllBookedItems();
    }


    @Operation(summary = "show available items", description = "API to show available items in the system.")
    @GetMapping("/available")
    public List<ItemResponseDto> getAllAvailableItems() {
        return itemService.getAllAvailableItems();
    }


    @Operation(summary = "show item by price range", description = "API to show items by price range in the system.")
    @GetMapping("/price-range")
    public List<ItemResponseDto> getItemsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return itemService.getItemsByPriceRange(minPrice, maxPrice);
    }


    @Operation(summary = "update items by booked", description = "API to update items booked or not in the system")
    @PatchMapping("/booked/{id}")
    public ItemResponseDto updateItemBooked(@PathVariable Long id) {
        return itemService.updateItemBooked(id, true);
    }


    @Operation(summary = "update item to unbook", description = "API to make item available")
    @PatchMapping("/unbooked/{id}")
    public ItemResponseDto updateItemUnbooked(@PathVariable Long id) {
        return itemService.updateItemBooked(id, false);
    }


    @Operation(summary = "show items by id", description = "API to get items by id")
    @GetMapping("/show/{id}")
    public ItemResponseDto getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }


    @Operation(summary = "show items by category", description = "API to show items by category")
    @GetMapping("/show-category/{id}")
    public ItemResponseDto getItemsByCategory(@PathVariable Long id) {
        return itemService.getItemsByCategory(id);
    }

}
