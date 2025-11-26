package com.orm.exam.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.TagEntity;

/*
 * Репозиторий для работы с тегами курсов
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID>{
    @Modifying
    @Query("DELETE FROM TagEntity t WHERE t.id = :id")
    void deleteById(UUID id);
}
