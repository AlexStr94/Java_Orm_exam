package com.orm.exam.dto;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LessonFullDto extends LessonDto{
    private String content;
    private Integer orderIndex;
    private UUID moduleId;
}
