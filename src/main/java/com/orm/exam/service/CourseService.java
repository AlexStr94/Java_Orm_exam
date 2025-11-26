package com.orm.exam.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.orm.exam.dto.CourseDto;
import com.orm.exam.dto.create.CreateCourseDto;
import com.orm.exam.entity.CategoryEntity;
import com.orm.exam.entity.CourseEntity;
import com.orm.exam.entity.TagEntity;
import com.orm.exam.entity.UserEntity;
import com.orm.exam.mapper.CourseMapper;
import com.orm.exam.repository.CategoryRepository;
import com.orm.exam.repository.CourseRepository;
import com.orm.exam.repository.TagRepository;
import com.orm.exam.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
    private CategoryRepository categoryRepository;
    private TagRepository tagRepository;
    private UserRepository userRepository;
    private CourseMapper courseMapper;

    @Transactional
    public CourseDto createCourse(CreateCourseDto dto) {
        CourseEntity course = courseMapper.prepareEntityForCreate(dto);

        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            List<CategoryEntity> categoriesList = categoryRepository.findAllById(dto.getCategoryIds());
            Set<CategoryEntity> categoriesSet = new HashSet<>(categoriesList);
            course.setCategories(categoriesSet);
        }

        if (dto.getTagIds() != null && !dto.getTagIds().isEmpty()) {
            List<TagEntity> tagsList = tagRepository.findAllById(dto.getTagIds());
            Set<TagEntity> tagsSet = new HashSet<>(tagsList);
            course.setTags(tagsSet);
        }

        if (dto.getTeacherIds() != null && !dto.getTeacherIds().isEmpty()) {
            List<UserEntity> teachersList = userRepository.findAllById(dto.getTeacherIds());
            Set<UserEntity> teachersSet = new HashSet<>(teachersList);
            course.setTeachers(teachersSet);
        }

        courseRepository.save(course);
        return courseMapper.toDto(course);
    }
}
