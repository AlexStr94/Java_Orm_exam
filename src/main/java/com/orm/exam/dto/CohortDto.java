package com.orm.exam.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CohortDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID courseId;
    private Integer number;
}
