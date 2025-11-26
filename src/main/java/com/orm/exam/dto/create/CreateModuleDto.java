package com.orm.exam.dto.create;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateModuleDto {
    private String title;
    private Integer orderIndex;
    private String description;
    private UUID courseId;
}
