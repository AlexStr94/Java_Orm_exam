package com.orm.exam.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.LessonEntity;

/*
 * Репозиторий для работы с уроками
 */
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {
    @Modifying
    @Query("DELETE FROM LessonEntity l WHERE l.id = :id")
    void deleteById(UUID id);

    @EntityGraph(attributePaths = "module")
    Optional<LessonEntity> findById(UUID id);

    @EntityGraph(attributePaths = "module")
    List<LessonEntity> findAll();

    @Query(value = """
        UPDATE lessons
           SET title   = :#{#entity.title},
               content = :#{#entity.content}
         WHERE id = :#{#entity.id}
        RETURNING *
        """, nativeQuery = true)
    LessonEntity updateLesson(@Param("entity") LessonEntity entity);
}
