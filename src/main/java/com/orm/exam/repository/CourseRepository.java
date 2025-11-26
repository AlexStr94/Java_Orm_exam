package com.orm.exam.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID>{
    
}
