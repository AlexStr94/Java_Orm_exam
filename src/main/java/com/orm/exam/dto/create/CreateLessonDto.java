package com.orm.exam.dto.create;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLessonDto {
    @NotBlank
    private String title;

    @NotNull
    private Integer orderIndex;

    @NotBlank
    private String content;

    @NotNull
    private UUID moduleId;
}
