package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity{
    @Column(name = "title", nullable = false, length = 200)
    @NotBlank
    private String title;

    @Column(name = "description", length = 5000)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "courses_teachers",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> teachers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "courses_categories",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "courses_tags",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CohortEntity> cohorts = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ModuleEntity> modules = new HashSet<>();

    public void addTeacher(@NonNull UserEntity teacher) {
        this.teachers.add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTeacher(@NonNull UserEntity teacher) {
        if (teachers.contains(teacher)){
            teachers.remove(teacher);
            teacher.getCourses().remove(this);
        }
    }

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
    }

    public void removeCategory(@NonNull CategoryEntity category) {
        if (categories.contains(category)){
            categories.remove(category);
        }
    }

    public void removeCohort(@NonNull CohortEntity cohort) {
        if (cohorts.contains(cohort)) {
            cohorts.remove(cohort);
            cohort.setCourse(null);
        }
    }

    public void removeModule(@NonNull ModuleEntity module) {
        if (modules.contains(module)) {
            modules.remove(module);
            module.setCourse(null);
        }
    }

    public void addTag(@NonNull TagEntity tag) {
        tags.add(tag);
        tag.getCourses().add(this);
    }

    public void removeTag(@NonNull TagEntity tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
            tag.getCourses().remove(this);
        }
    }
}
