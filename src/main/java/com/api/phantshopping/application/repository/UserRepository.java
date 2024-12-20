package com.api.phantshopping.application.repository;

import com.api.phantshopping.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAll();
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
