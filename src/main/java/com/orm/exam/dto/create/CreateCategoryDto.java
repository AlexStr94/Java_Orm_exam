package com.orm.exam.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank
    @Size(min = 5, max = 40)
    private String name;
}
