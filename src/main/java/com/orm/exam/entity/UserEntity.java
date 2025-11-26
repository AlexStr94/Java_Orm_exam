package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    @Email(message = "Email должен быть в корректном формате")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private ProfileEntity profile;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    private Set<CourseEntity> courses = new HashSet<>();

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private Set<CohortEntity> cohorts = new HashSet<>();
}
