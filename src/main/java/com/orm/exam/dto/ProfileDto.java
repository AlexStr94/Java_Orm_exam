package com.orm.exam.dto;

import java.util.UUID;

import com.orm.exam.dto.create.CreateProfileDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProfileDto extends CreateProfileDto{
    private UUID id;
}
