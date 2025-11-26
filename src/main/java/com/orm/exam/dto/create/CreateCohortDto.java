package com.orm.exam.dto.create;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateCohortDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID courseId;
}
