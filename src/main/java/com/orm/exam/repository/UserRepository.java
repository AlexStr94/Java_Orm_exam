package com.orm.exam.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, UUID>{
    @EntityGraph(attributePaths = "profile")
    Optional<UserEntity> findById(UUID id);
}
