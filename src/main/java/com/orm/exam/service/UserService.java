package com.orm.exam.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orm.exam.dto.UserDto;
import com.orm.exam.dto.create.CreateUserDto;
import com.orm.exam.entity.UserEntity;
import com.orm.exam.mapper.UserMapper;
import com.orm.exam.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserDto getUserById(UUID id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с идентификатором " + id + " не найден."));
        return userMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Transactional
    public UserDto createUser(CreateUserDto dto) {
        UserEntity user = userMapper.prepareEntityToCreate(dto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
