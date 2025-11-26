package com.orm.exam.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm.exam.dto.QuizDto;
import com.orm.exam.dto.create.CreateQuizDto;
import com.orm.exam.service.QuizService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/quizzes")
@AllArgsConstructor
public class QuizController {
    private QuizService quizService;

    /**
     * Получить тест по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable UUID id) {
        QuizDto dto = quizService.getQuizById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Получить все тесты
     */
    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        List<QuizDto> dtos = quizService.getAllQuizzes();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Создать новый тест
     */
    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody CreateQuizDto createDto) {
        QuizDto createdDto = quizService.createQuiz(createDto);
        return ResponseEntity.ok(createdDto);
    }

    /**
     * Удалить тест по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
