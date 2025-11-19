package com.ecommerce.ecommerce.dto.request;

public class ItemRequestDto {
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private Long categoryId;

    public ItemRequestDto() {
    }


    public ItemRequestDto(String title, String description, double price, String imagePath, Long categoryId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
