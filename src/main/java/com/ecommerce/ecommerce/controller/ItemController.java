package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.ItemRequestDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")

public class ItemController {
    @Autowired
    ItemService itemService;
    @PostMapping("/create")
    public ItemResponseDto createItem(@RequestBody ItemRequestDto itemDto){
        return itemService.createItem(itemDto);
    }


    @PutMapping("/update/{id}")
    public ItemResponseDto updateItem(@PathVariable Long id , @RequestBody ItemRequestDto itemDto){

        return itemService.updateItem(id , itemDto);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id){
         itemService.deleteItem(id);
    }

    @GetMapping("/List")
    public List<ItemResponseDto> getAllItems (){
        return itemService.getAllItems();
    }


    @GetMapping("/booked")
    public List<ItemResponseDto> getAllBookedItems(){
        return itemService.getAllBookedItems();
    }



    @GetMapping("/available")
    public List<ItemResponseDto> getAllAvailableItems(){
        return itemService.getAllAvailableItems();
    }


    @GetMapping("/price-range")
    public List<ItemResponseDto> getItemsByPriceRange(@RequestParam double minPrice , @RequestParam double maxPrice){
        return itemService.getItemsByPriceRange(minPrice , maxPrice);
    }


    @PatchMapping("/booked/{id}")
    public ItemResponseDto updateItemBooked(@PathVariable Long id){
        return itemService.updateItemBooked(id , true);
    }




    @PatchMapping("/unbooked/{id}")
    public ItemResponseDto updateItemUnbooked(@PathVariable Long id){
        return itemService.updateItemBooked(id , false);
    }


    @GetMapping("/show/{id}")
    public ItemResponseDto getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }


    @GetMapping("/show-category/{id}")
    public List<ItemResponseDto> getItemsByCategory(@PathVariable Long id){
        return itemService.getItemsByCategory(id);
    }

}
