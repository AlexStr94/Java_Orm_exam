package com.orm.exam.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class TagDto {
    private UUID id;
    private String name;
}
