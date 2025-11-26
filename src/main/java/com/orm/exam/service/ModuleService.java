package com.orm.exam.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orm.exam.dto.ModuleDto;
import com.orm.exam.dto.create.CreateModuleDto;
import com.orm.exam.entity.ModuleEntity;
import com.orm.exam.mapper.ModuleMapper;
import com.orm.exam.repository.ModuleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModuleService {
    private ModuleRepository moduleRepository;
    private ModuleMapper moduleMapper;

    @Transactional(readOnly = true)
    public ModuleDto getModuleById(UUID id) {
        ModuleEntity entity = moduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Модуль с идентификатором " + id + " не найден."));
        return moduleMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ModuleDto> getAllModulesByCourseId(UUID courseID) {
        return moduleRepository.findByCourseId(courseID).stream()
                .map(moduleMapper::toDto)
                .toList();
    }

    @Transactional
    public ModuleDto createModule(CreateModuleDto dto) {
        ModuleEntity entity = moduleMapper.prepareEntityForCreate(dto);
        entity = moduleRepository.save(entity);
        return moduleMapper.toDto(entity);
    }

    @Transactional
    public void deleteModule(UUID id) {
        moduleRepository.deleteById(id);
    }
}
