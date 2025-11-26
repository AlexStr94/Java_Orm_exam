package com.orm.exam.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.orm.exam.dto.TagDto;
import com.orm.exam.entity.TagEntity;
import com.orm.exam.mapper.TagMapper;
import com.orm.exam.repository.TagRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagDto> getAllTags() {
        return tagRepository.findAll().stream().map(tagMapper::toDto).collect(Collectors.toList());
    }

    public TagDto getTagById(UUID id) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Тег с id: " + id + " не найден"));
        return tagMapper.toDto(entity);
    }

    public TagDto createTag(TagDto tagDto) {
        TagEntity entity = tagMapper.toEntity(tagDto);
        entity.setId(null);
        TagEntity savedEntity = tagRepository.save(entity);
        return tagMapper.toDto(savedEntity);
    }

    public TagDto updateTag(UUID id, TagDto tagDto) {
        TagEntity entity = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Тег с id: " + id + " не найден"));
        entity.setName(tagDto.getName());
        tagRepository.save(entity);
        return tagMapper.toDto(entity);
    }

    public void deleteTag(UUID id) {
        tagRepository.deleteById(id);
    }
}
