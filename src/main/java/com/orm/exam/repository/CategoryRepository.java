package com.orm.exam.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.orm.exam.entity.CategoryEntity;

/*
 * Репозиторий для работы с категориями курсов
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID>{
    @Modifying
    @Query("DELETE FROM CategoryEntity c WHERE c.id = :id")
    void deleteById(UUID id);
}
