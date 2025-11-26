package com.orm.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseEntity{
    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String MiddleName;

    @Column(name = "bio", length = 1000, nullable = true)
    private String bio;
}
