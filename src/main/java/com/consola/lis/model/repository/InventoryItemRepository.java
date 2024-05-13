package com.consola.lis.model.repository;

import com.consola.lis.model.entity.InventoryItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

    @Query("SELECT p FROM InventoryItem p ")
    Page<InventoryItem> findAllItems(Pageable pageable);
}