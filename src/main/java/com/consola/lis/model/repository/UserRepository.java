package com.consola.lis.model.repository;

import com.consola.lis.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsUserNameByName(String username);

    boolean existsUserNameByIdAndUsername(String username,String id);
    boolean existsUserNameByIdOrUsername(String username, String id);

    Optional<User> findByUsernameOrId(String username, String id);
    Optional<User> findByUsernameAndId(String username, String id);
}
