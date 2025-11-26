package com.orm.exam.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class CourseDto {
    private UUID id;
    private String title;
    private String description;
    private Set<TeacherDto> teachers;
    private Set<CategoryDto> categories;
    private Set<TagDto> tags;
}
