package com.orm.exam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.TagDto;
import com.orm.exam.entity.TagEntity;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(TagEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "courses", ignore = true)
    TagEntity toEntity(TagDto dto);
}
