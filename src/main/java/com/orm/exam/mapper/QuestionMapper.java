package com.orm.exam.mapper;

import java.util.Set;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.AnswerOptionDto;
import com.orm.exam.dto.QuestionDto;
import com.orm.exam.dto.create.CreateQuestionDto;
import com.orm.exam.entity.AnswerOptionEntity;
import com.orm.exam.entity.QuestionEntity;
import com.orm.exam.entity.QuizEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "quiz.id", target = "quizId")
    QuestionDto toDto(QuestionEntity entity);

    Set<AnswerOptionDto> toAnswerDtoSet(Set<AnswerOptionEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "quizId", target = "quiz")
    QuestionEntity prepareEntityForCreate(CreateQuestionDto dto);

    default QuizEntity map(UUID id) {
        if (id == null) return null;
        QuizEntity quiz = new QuizEntity();
        quiz.setId(id);
        return quiz;
    }
}
