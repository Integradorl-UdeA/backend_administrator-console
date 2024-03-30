package com.consola.lis.model.repository;


import com.consola.lis.model.entity.QuantizableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuantizableItemRepository extends JpaRepository<QuantizableItem, String> {

    Optional<QuantizableItem> findByItemId(String itemId);

}
