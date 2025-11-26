package com.orm.exam.dto.update;

import lombok.Data;

@Data
public class UpdateLessonDto {
    private String title;
    private String content;
    private Integer orderIndex;
}
