package com.orm.exam.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orm.exam.dto.QuestionDto;
import com.orm.exam.dto.create.CreateQuestionDto;
import com.orm.exam.service.QuestionServise;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private QuestionServise questionService;

    /**
     * Получить вопрос по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable UUID id) {
        QuestionDto dto = questionService.getQuestionById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Получить все вопросы
     */
    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> dtos = questionService.getAllQuestions();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Создать новый вопрос
     */
    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody CreateQuestionDto createDto) {
        QuestionDto createdDto = questionService.createQuestion(createDto);
        return ResponseEntity.ok(createdDto);
    }

    /**
     * Удалить вопрос по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}