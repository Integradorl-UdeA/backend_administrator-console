package com.consola.lis.model.repository;

import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHelloLisRepository extends JpaRepository<UserHelloLis, Integer > {

    Optional<UserHelloLis> findByUserLis(UserLis userLis);
}
