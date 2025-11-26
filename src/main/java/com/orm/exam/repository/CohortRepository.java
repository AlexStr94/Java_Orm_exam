package com.orm.exam.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orm.exam.entity.CohortEntity;

/*
 * Репозиторий для работы с потоками на курсах
 */
@Repository
public interface CohortRepository extends JpaRepository<CohortEntity, UUID> {
    public List<CohortEntity> findAllByCourseId(UUID id);

@Modifying(flushAutomatically = true, clearAutomatically = true)
@Query(value = """
    INSERT INTO cohorts (id, created_at, start_date, end_date, number, course_id)
    VALUES (
        :id,
        NOW(),
        :startDate,
        :endDate,
        (
            SELECT COALESCE(MAX(number), 0) + 1
            FROM cohorts
            WHERE course_id = :courseId
        ),
        :courseId
    )
    """, nativeQuery = true)
    void insertWithNextNumber(
            @Param("id") UUID id,
            @Param("courseId") UUID courseId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    
    /**
     * Блокирует все потоки одного курса
     */
    @Query(value = "SELECT 1 FROM courses WHERE id = :courseId FOR UPDATE", nativeQuery = true)
    void lockCourseById(@Param("courseId") UUID courseId);
   
}
