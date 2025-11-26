package com.orm.exam.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.orm.exam.dto.UserDto;
import com.orm.exam.dto.create.CreateProfileDto;
import com.orm.exam.dto.create.CreateUserDto;
import com.orm.exam.entity.ProfileEntity;
import com.orm.exam.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "cohorts", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "profile", source = "profile")
    UserEntity prepareEntityToCreate(CreateUserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    ProfileEntity prepareProfileEntityToCreate(CreateProfileDto dto);

    @AfterMapping
    default void linkProfileToUser(@MappingTarget UserEntity user) {
        ProfileEntity profile = user.getProfile();
        if (profile != null) {
            profile.setUser(user);
        }
    }
}

