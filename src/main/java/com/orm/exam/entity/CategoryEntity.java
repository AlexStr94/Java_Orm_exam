package com.orm.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
