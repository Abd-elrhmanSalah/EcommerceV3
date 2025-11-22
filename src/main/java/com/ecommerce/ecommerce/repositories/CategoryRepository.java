package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);

    Optional<Category> findByIdAndIsDeletedFalse(Long id);
}
