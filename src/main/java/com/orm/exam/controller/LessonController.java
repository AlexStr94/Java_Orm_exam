package com.orm.exam.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm.exam.dto.LessonFullDto;
import com.orm.exam.dto.create.CreateLessonDto;
import com.orm.exam.dto.update.UpdateLessonDto;
import com.orm.exam.service.LessonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {
    private LessonService lessonService;

    @GetMapping
    public List<LessonFullDto> getAllTags() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public LessonFullDto getLessonById(@PathVariable("id") UUID id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping
    public LessonFullDto createLesson(@Valid @RequestBody CreateLessonDto dto) {
        return lessonService.createLesson(dto);
    }

    @PutMapping("/{id}")
    public LessonFullDto updateLesson(@PathVariable("id") UUID id, @Valid @RequestBody UpdateLessonDto dto) {
        return lessonService.updateLesson(id, dto);
    }
}
