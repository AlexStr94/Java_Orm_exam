package com.orm.exam.dto.create;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateCourseDto {
    private String title;
    private String description;
    private Set<UUID> categoryIds;
    private Set<UUID> tagIds;
    private Set<UUID> teacherIds;
}
