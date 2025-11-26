package com.orm.exam.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orm.exam.dto.LessonFullDto;
import com.orm.exam.dto.create.CreateLessonDto;
import com.orm.exam.dto.update.UpdateLessonDto;
import com.orm.exam.entity.LessonEntity;
import com.orm.exam.exception.EntityNotFoundException;
import com.orm.exam.mapper.LessonMapper;
import com.orm.exam.repository.LessonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService {
    private LessonRepository lessonRepository;

    private LessonMapper lessonMapper;

    @Transactional(readOnly = true)
    public LessonFullDto getLessonById(UUID id) {
        LessonEntity entity = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Урок с идентифкатором " + id + " не найден."));
        return lessonMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<LessonFullDto> getAllLessons() {
        return lessonRepository.findAll().stream().map(lessonMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public LessonFullDto createLesson(CreateLessonDto dto) {
        LessonEntity entity = lessonMapper.prepareEntityForCreate(dto);
        LessonEntity saved = lessonRepository.save(entity);
        return lessonMapper.toDto(saved);
    }

    @Transactional
    public LessonFullDto updateLesson(UUID id, UpdateLessonDto dto) {
        LessonEntity entity = lessonMapper.prepareEntityForUpdate(dto);
        entity.setId(id);

        LessonEntity updated = lessonRepository.updateLesson(entity);
        return lessonMapper.toDto(updated);
    }

}
