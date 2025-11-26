package com.orm.exam.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.LessonFullDto;
import com.orm.exam.dto.create.CreateLessonDto;
import com.orm.exam.dto.update.UpdateLessonDto;
import com.orm.exam.entity.LessonEntity;
import com.orm.exam.entity.ModuleEntity;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    @Mapping(source = "module.id", target = "moduleId")
    LessonFullDto toDto(LessonEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "moduleId", target = "module")
    @Mapping(target = "assignments", ignore = true)
    LessonEntity toEntity(LessonFullDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "moduleId", target = "module")
    @Mapping(target = "assignments", ignore = true)
    LessonEntity prepareEntityForCreate(CreateLessonDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "module", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    LessonEntity prepareEntityForUpdate(UpdateLessonDto dto);

    default ModuleEntity map(UUID id) {
        if (id == null) return null;
        ModuleEntity module = new ModuleEntity();
        module.setId(id);
        return module;
    }
}