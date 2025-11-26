package com.orm.exam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.orm.exam.dto.CategoryDto;
import com.orm.exam.dto.create.CreateCategoryDto;
import com.orm.exam.dto.update.UpdateCategoryDto;
import com.orm.exam.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(CategoryEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    CategoryEntity toEntity(CategoryDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    CategoryEntity prepareEntityForCreate(CreateCategoryDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    CategoryEntity prepareEntityForUpdate(UpdateCategoryDto dto);
}
