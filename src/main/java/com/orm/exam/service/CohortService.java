package com.orm.exam.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.orm.exam.dto.CohortDto;
import com.orm.exam.dto.create.CreateCohortDto;
import com.orm.exam.entity.CohortEntity;
import com.orm.exam.exception.EntityNotFoundException;
import com.orm.exam.mapper.CohortMapper;
import com.orm.exam.repository.CohortRepository;
import com.orm.exam.repository.CourseRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CohortService {
    private CourseRepository courseRepository;
    private CohortRepository cohortRepository;
    private CohortMapper cohortMapper;

    public CohortDto getCohortById(UUID id) {
        CohortEntity entity = cohortRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Поток с идентификатором " + id + " не найден."));
        return cohortMapper.toDto(entity);
    }

    public List<CohortDto> getAllCohortsByCourseId(UUID courseId) {
        return cohortRepository.findAllByCourseId(courseId).stream().map(cohortMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CohortDto createCohort(CreateCohortDto dto) {
        UUID courseId = dto.getCourseId();
        UUID cohortId = UUID.randomUUID();

        if (!courseRepository.existsById(courseId)) {
            throw new EntityNotFoundException("Курс с идентификатором " + courseId + " не найден.");
        }

        cohortRepository.lockCourseById(courseId);

        cohortRepository.insertWithNextNumber(
                cohortId,
                courseId,
                dto.getStartDate(),
                dto.getEndDate());


        CohortEntity entity = cohortRepository.findById(cohortId)
                .orElseThrow(() -> new IllegalStateException("Когорта не была создана"));

        return cohortMapper.toDto(entity);
    }

}
