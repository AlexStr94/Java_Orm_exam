package com.orm.exam.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orm.exam.dto.QuizDto;
import com.orm.exam.dto.create.CreateQuizDto;
import com.orm.exam.entity.QuizEntity;
import com.orm.exam.exception.EntityNotFoundException;
import com.orm.exam.mapper.QuizMapper;
import com.orm.exam.repository.QuizRepository;

import lombok.AllArgsConstructor;

/*
 * Сервис для работы с тестами
 */
@Service
@AllArgsConstructor
public class QuizService {
    private QuizRepository quizRepository;
    private QuizMapper quizMapper;

    @Transactional(readOnly = true)
    public QuizDto getQuizById(UUID id) {
        QuizEntity entity = quizRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Тест с идентифкатором " + id + " не найден."));
        return quizMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(quizMapper::toDto)
                .toList();
    }

    @Transactional
    public QuizDto createQuiz(CreateQuizDto quizDto) {
        QuizEntity entity = quizMapper.prepareEntityForCreate(quizDto);
        entity = quizRepository.save(entity);
        return quizMapper.toDto(entity);
    } 

    @Transactional
    public void deleteQuiz(UUID id) {
        quizRepository.deleteById(id);
    }
}
