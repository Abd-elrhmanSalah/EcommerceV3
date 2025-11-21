package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private Boolean booked = false;
    private CategoryResponseDto category;
}
