package com.api.phantshopping.application.repository;

import com.api.phantshopping.domain.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>
{

    List<Item> findAll();

    List<Item> findItemByListId(UUID listId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM items WHERE id = :id", nativeQuery = true)
    void deleteItemByIdNative(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM items WHERE list_id = :listId", nativeQuery = true)
    void deleteItemsByListId(@Param("listId") UUID listId);
}
