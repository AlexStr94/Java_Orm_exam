package com.orm.exam.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/*
 * Модель вопроса теста
 */
@Setter
@Getter
@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @Column(name = "text", nullable = false)
    private String text;

    @Enumerated
    @Column(name = "type", nullable = false)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AnswerOptionEntity> answers = new HashSet<>();

    public void addAnswer(@NonNull AnswerOptionEntity answer) {
        answers.add(answer);
    }

    public void removeAnswer(@NonNull AnswerOptionEntity answer) {
        if (answers.contains(answer)) {
            answers.remove(answer);
        }
    }
}
