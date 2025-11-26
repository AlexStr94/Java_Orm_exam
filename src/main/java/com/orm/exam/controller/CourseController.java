package com.orm.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.orm.exam.dto.CourseDto;
import com.orm.exam.dto.create.CreateCourseDto;
import com.orm.exam.service.CourseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    /**
     * Создание курса.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@Valid @RequestBody CreateCourseDto createCourseDto) {
        return courseService.createCourse(createCourseDto);
    }
}
