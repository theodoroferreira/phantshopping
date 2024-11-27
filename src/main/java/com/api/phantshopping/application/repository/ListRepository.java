package com.api.phantshopping.application.repository;

import com.api.phantshopping.domain.model.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListRepository extends JpaRepository<List, UUID>
{

    java.util.List<List> findListByUserId(UUID userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lists WHERE id = :id", nativeQuery = true)
    void deleteListByIdNative(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lists WHERE user_id = :userId", nativeQuery = true)
    void deleteListsByUserId(@Param("userId") UUID userId);
}
