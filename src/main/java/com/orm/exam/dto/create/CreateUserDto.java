package com.orm.exam.dto.create;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private Boolean isAdmin;
    private CreateProfileDto profile;
}
