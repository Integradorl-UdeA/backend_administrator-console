package com.consola.lis.model.repository;

import com.consola.lis.model.entity.GeneralItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralItemRepository extends JpaRepository<GeneralItem, String> {
}
