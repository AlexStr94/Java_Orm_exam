package com.orm.exam.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm.exam.dto.TagDto;
import com.orm.exam.service.TagService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {
    private TagService tagService;

    @GetMapping
    public List<TagDto> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable("id") UUID id) {
        TagDto tagDto = tagService.getTagById(id);
        return ResponseEntity.ok(tagDto);
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDTO) {
        TagDto createdTag = tagService.createTag(tagDTO);
        return ResponseEntity.ok(createdTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable("id") UUID id, @Valid @RequestBody TagDto tagDTO) {
        TagDto updatedTag = tagService.updateTag(id, tagDTO);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
