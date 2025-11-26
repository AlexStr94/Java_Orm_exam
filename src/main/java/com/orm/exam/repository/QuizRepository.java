package com.orm.exam.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.QuizEntity;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, UUID>{
    @Modifying
    @Query("DELETE FROM QuizEntity q WHERE q.id = :id")
    void deleteById(UUID id);
}
