package com.orm.exam.entity;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * Модель решения задания.
 */
@Setter
@Getter
@Entity
@Table(name = "submissions")
@Check(constraints = "score IN (0, 3, 4, 5)", name = "chk_submission_score")
public class SubmissionEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private AssignmentEntity assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "content")
    private String content;

    @Column(name = "score", nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private Integer score = 0;

    @Column(name = "review", length = 2000)
    private String review;

}
