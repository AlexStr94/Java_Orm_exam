package com.orm.exam.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AnswerOptionDto {
    private UUID id;
    private String text;
    private Boolean isCorrect;
}
