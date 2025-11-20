package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<SystemUser, Long> {

    boolean existsByEmail(String email);

}