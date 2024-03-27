package com.consola.lis.model.repository;

import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
    void deleteByCategoryName(String categoryName);


}
