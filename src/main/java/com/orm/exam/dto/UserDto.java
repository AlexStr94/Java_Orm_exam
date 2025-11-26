package com.orm.exam.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private Boolean isAdmin;
    private ProfileDto profile;
}
