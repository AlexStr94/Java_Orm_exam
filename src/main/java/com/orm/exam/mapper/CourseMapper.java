package com.orm.exam.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.orm.exam.dto.CategoryDto;
import com.orm.exam.dto.CourseDto;
import com.orm.exam.dto.TagDto;
import com.orm.exam.dto.TeacherDto;
import com.orm.exam.dto.create.CreateCourseDto;
import com.orm.exam.entity.CategoryEntity;
import com.orm.exam.entity.CourseEntity;
import com.orm.exam.entity.TagEntity;
import com.orm.exam.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseDto toDto(CourseEntity entity);

    @Mapping(source = "profile.firstName", target = "firstName")
    @Mapping(source = "profile.lastName", target = "lastName")
    @Mapping(source = "profile.middleName", target = "middleName")
    TeacherDto toTeacherDto(UserEntity user);

    Set<TagDto> toTagDtoSet(Set<TagEntity> tags);
    Set<CategoryDto> toCategoryDtoSet(Set<CategoryEntity> categories);
    Set<TeacherDto> toTeacherDtoSet(Set<UserEntity> teachers);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "cohorts", ignore = true)
    @Mapping(target = "modules", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    CourseEntity prepareEntityForCreate(CreateCourseDto dto);
}
