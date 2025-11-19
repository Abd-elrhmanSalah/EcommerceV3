package com.ecommerce.ecommerce.dto.response;

public class ItemResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private boolean booked;
    private String categoryTitle;

    public ItemResponseDto() {
    }

    public ItemResponseDto(Long id, String title, String description, double price, String imagePath, boolean booked, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.booked = booked;
        this.categoryTitle = categoryTitle;
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

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
