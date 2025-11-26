package com.orm.exam.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm.exam.dto.ModuleDto;
import com.orm.exam.dto.create.CreateModuleDto;
import com.orm.exam.service.ModuleService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {
    private ModuleService moduleService;

    /*
     * Получить модуль по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModuleDto> getModuleById(@PathVariable UUID id) {
        ModuleDto dto = moduleService.getModuleById(id);
        return ResponseEntity.ok(dto);
    }

    /*
     * Cоздать модуль
     */
    @PostMapping
    public ResponseEntity<ModuleDto> createModule(@Valid @RequestBody CreateModuleDto dto) {
        ModuleDto module = moduleService.createModule(dto);
        return ResponseEntity.ok(module);
    }

    /*
     * Удалить модуль
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable UUID id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
