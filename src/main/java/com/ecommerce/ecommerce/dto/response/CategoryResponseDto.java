package com.ecommerce.ecommerce.dto.response;

import java.util.List;

public class CategoryResponseDto {
    private Long id;
    private String title;
    private List<ItemResponseDto> items;



    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Long id, String title, List<ItemResponseDto> items) {
        this.id = id;
        this.title = title;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<ItemResponseDto> items) {
        this.items = items;
    }
}
