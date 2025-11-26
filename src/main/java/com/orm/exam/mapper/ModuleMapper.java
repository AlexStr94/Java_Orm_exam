package com.orm.exam.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.ModuleDto;
import com.orm.exam.dto.create.CreateModuleDto;
import com.orm.exam.entity.CourseEntity;
import com.orm.exam.entity.ModuleEntity;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    @Mapping(source = "course.id", target = "courseId")
    ModuleDto toDto(ModuleEntity entity);
    

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(source = "courseId", target = "course")
    ModuleEntity prepareEntityForCreate(CreateModuleDto dto);

    default CourseEntity map(UUID id){
        if (id == null) return null;
        CourseEntity course = new CourseEntity();
        course.setId(id);
        return course;
    }
}
