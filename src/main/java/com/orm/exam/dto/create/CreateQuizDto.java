package com.orm.exam.dto.create;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateQuizDto {
    private String title;
    private UUID moduleId;
}
