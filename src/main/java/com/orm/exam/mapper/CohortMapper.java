package com.orm.exam.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.orm.exam.dto.CohortDto;
import com.orm.exam.dto.create.CreateCohortDto;
import com.orm.exam.entity.CohortEntity;
import com.orm.exam.entity.CourseEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CohortMapper {
    @Mapping(source = "course.id", target = "courseId")
    CohortDto toDto(CohortEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(source = "courseId", target = "course")
    CohortEntity prepareEntityForCreate(CreateCohortDto dto);

    default CourseEntity mapToCourseEntity(UUID courseId) {
        if (courseId == null) {
            return null;
        }
        CourseEntity course = new CourseEntity();
        course.setId(courseId);
        return course;
    }
}
