package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * Модель тега для курсов
 */
@Setter
@Getter
@Entity
@Table(name = "tags")
public class TagEntity extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<CourseEntity> courses = new HashSet<>();
}
