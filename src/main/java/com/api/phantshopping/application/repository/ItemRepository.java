package com.api.phantshopping.application.repository;

import com.api.phantshopping.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>
{

    List<Item> findAll();

    List<Item> findItemByListId(UUID listId);
}
