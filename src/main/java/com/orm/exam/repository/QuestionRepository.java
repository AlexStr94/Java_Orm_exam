package com.orm.exam.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID>{
    @Modifying
    @Query("DELETE FROM QuestionEntity q WHERE q.id = :id")
    void deleteById(UUID id);

    @EntityGraph(attributePaths = {"quiz", "answers"})
    Optional<QuestionEntity> findById(UUID id);
}
