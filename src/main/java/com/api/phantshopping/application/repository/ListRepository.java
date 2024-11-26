package com.api.phantshopping.application.repository;

import com.api.phantshopping.domain.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListRepository extends JpaRepository<List, UUID>
{

    java.util.List<List> findListByUserId(UUID userId);

}
