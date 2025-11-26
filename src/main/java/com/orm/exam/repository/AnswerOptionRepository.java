package com.orm.exam.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.AnswerOptionEntity;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptionEntity, UUID>{
    @Modifying
    @Query("DELETE FROM AnswerOptionEntity a WHERE a.id = :id")
    void deleteById(UUID id);
}
