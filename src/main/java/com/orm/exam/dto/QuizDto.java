package com.orm.exam.dto;

import java.util.UUID;

import com.orm.exam.dto.create.CreateQuizDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuizDto extends CreateQuizDto{
    private UUID id;
}
