package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Модель модуля курса
 */
@Setter
@Getter
@Entity
@Table(name = "modules")
public class ModuleEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LessonEntity> lessons = new HashSet<>();

    @NotBlank
    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToOne(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private QuizEntity quiz;

    public void addLesson(@NonNull LessonEntity lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(@NonNull LessonEntity lesson) {
        if (lessons.contains(lesson)) {
            lessons.remove(lesson);
        }
    }
}
