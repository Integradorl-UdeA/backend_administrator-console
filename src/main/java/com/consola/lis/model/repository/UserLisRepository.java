package com.consola.lis.model.repository;

import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLisRepository extends JpaRepository<UserLis, String> {

    Optional<UserLis> findByUsername(String username);

    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
