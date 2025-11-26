package com.orm.exam.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.orm.exam.dto.CategoryDto;
import com.orm.exam.dto.create.CreateCategoryDto;
import com.orm.exam.dto.update.UpdateCategoryDto;
import com.orm.exam.entity.CategoryEntity;
import com.orm.exam.mapper.CategoryMapper;
import com.orm.exam.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(UUID id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория с id: " + id + " не найден"));
        return categoryMapper.toDto(entity);
    }

    public CategoryDto createCategory(CreateCategoryDto dto) {
        CategoryEntity entity = categoryMapper.prepareEntityForCreate(dto);
        CategoryEntity savedEntity = categoryRepository.save(entity);
        return categoryMapper.toDto(savedEntity);
    }

    public CategoryDto updateCategory(UUID id, UpdateCategoryDto dto) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория с id: " + id + " не найден"));
        entity.setName(dto.getName());
        categoryRepository.save(entity);
        return categoryMapper.toDto(entity);
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
    
}
