package com.orm.exam.dto.create;

import lombok.Data;

@Data
public class CreateAnswerOptionDto {
    private String text;
    private Boolean isCorrect;
}
