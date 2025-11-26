package com.orm.exam.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.orm.exam.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
    @Modifying
    @Query("DELETE FROM ModuleEntity m WHERE m.id = :id")
    void deleteById(UUID id);

    @EntityGraph(attributePaths = {"course", "lessons"})
    Optional<ModuleEntity> findById(UUID id);
    
    @EntityGraph(attributePaths = "lessons")
    List<ModuleEntity> findByCourseId(UUID courseId);
}
