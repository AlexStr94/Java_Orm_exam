package com.orm.exam.dto.create;

import java.util.Set;
import java.util.UUID;

import com.orm.exam.entity.QuestionType;

import lombok.Data;

@Data
public class CreateQuestionDto {
    private String text;
    private QuestionType type;
    private UUID quizId;
    private Set<CreateAnswerOptionDto> answerOptions;
}
