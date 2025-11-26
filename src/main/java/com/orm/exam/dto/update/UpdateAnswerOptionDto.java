package com.orm.exam.dto.update;

import lombok.Data;

@Data
public class UpdateAnswerOptionDto {
    private String text;
    private Boolean isCorrect;
}
