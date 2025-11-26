package com.orm.exam.dto.update;

import lombok.Data;

@Data
public class UpdateModuleDto {
    private String title;
    private Integer orderIndex;
    private String description;
}
