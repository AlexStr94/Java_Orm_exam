package com.orm.exam.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class ModuleDto {
    private UUID id;
    private String title;
    private String description;
    private Integer orderIndex;
    private UUID courseId;
    private Set<LessonDto> lessons;
}
