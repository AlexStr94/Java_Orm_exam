package com.orm.exam.dto.update;

import java.util.UUID;

import lombok.Data;

@Data
public class UpdateQuizDto {
    private UUID id;
    private String title;
}
