package com.orm.exam.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.QuizDto;
import com.orm.exam.dto.create.CreateQuizDto;
import com.orm.exam.dto.update.UpdateQuizDto;
import com.orm.exam.entity.ModuleEntity;
import com.orm.exam.entity.QuizEntity;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    @Mapping(source = "module.id", target = "moduleId")
    QuizDto toDto(QuizEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "module", ignore = true)
    QuizEntity toEntity(QuizDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(source = "moduleId", target = "module")
    QuizEntity prepareEntityForCreate(CreateQuizDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "module", ignore = true)
    QuizEntity prepareEntityForUpdate(UpdateQuizDto dto);

    default ModuleEntity map(UUID id) {
        if (id == null) return null;
        ModuleEntity module = new ModuleEntity();
        module.setId(id);
        return module;
    }
}
