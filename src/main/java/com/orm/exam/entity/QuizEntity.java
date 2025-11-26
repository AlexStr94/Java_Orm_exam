package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/*
 * Модель теста в модуле.
 */
@Setter
@Getter
@Entity
@Table(name = "quizzes")
public class QuizEntity extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleEntity module;

    @Column(name = "title", length = 200)
    private String title;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionEntity> questions = new HashSet<>();

    public void addQuestion(@NonNull QuestionEntity question) {
        questions.add(question);
    }

    public void removeQuestion(@NonNull QuestionEntity question) {
        if (questions.contains(question)) {
            questions.remove(question);
        }
    }
}
