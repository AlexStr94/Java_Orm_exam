package com.orm.exam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.AnswerOptionDto;
import com.orm.exam.dto.create.CreateAnswerOptionDto;
import com.orm.exam.entity.AnswerOptionEntity;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper {
    AnswerOptionDto toDto(AnswerOptionEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "question", ignore = true)
    AnswerOptionEntity toEntity(AnswerOptionDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "question", ignore = true)
    AnswerOptionEntity prepareEntityForCreate(CreateAnswerOptionDto dto);
}
