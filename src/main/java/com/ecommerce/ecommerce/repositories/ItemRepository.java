package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByBooked(boolean booked);

    List<Item> findByPriceBetween(double min, double max);

    List<Item> findByCategoryId(Long categoryId);

}