package com.orm.exam.dto;

import java.util.Set;
import java.util.UUID;

import com.orm.exam.entity.QuestionType;

import lombok.Data;

@Data
public class QuestionDto {
    private UUID id;
    private String text;
    private QuestionType type;
    private UUID quizId;
    private Set<AnswerOptionDto> answers;
}
