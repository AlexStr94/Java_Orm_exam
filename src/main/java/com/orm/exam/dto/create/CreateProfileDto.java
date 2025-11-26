package com.orm.exam.dto.create;

import lombok.Data;

@Data
public class CreateProfileDto {
    private String lastName;
    private String firstName;
    private String MiddleName;
    private String bio;
}
