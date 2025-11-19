package com.ecommerce.ecommerce.dto.request;

public class CategoryRequestDto {
    private String title;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
