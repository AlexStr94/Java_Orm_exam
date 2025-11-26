package com.orm.exam.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.orm.exam.dto.create.CreateCohortDto;
import com.orm.exam.entity.CourseEntity;
import com.orm.exam.exception.EntityNotFoundException;
import com.orm.exam.repository.CourseRepository;

import com.orm.exam.repository.CohortRepository;

@SpringBootTest
class CohortServiceTest {

    @Autowired
    private CohortService cohortService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CohortRepository cohortRepository;

    private UUID courseId;

    @BeforeEach
    void setUp() {
        CourseEntity course = new CourseEntity();
        course.setTitle("Java Advanced");
        course = courseRepository.save(course);
        courseId = course.getId();
    }

    @Test
    void should_CreateCohorts_WithSequentialNumbers() {
        // Создаём когорту 1
        CreateCohortDto dto1 = new CreateCohortDto();
        dto1.setCourseId(courseId);
        dto1.setStartDate(LocalDate.now());
        dto1.setEndDate(LocalDate.now().plusMonths(3));

        var result1 = cohortService.createCohort(dto1);
        assertEquals(1, result1.getNumber());

        // Создаём когорту 2
        CreateCohortDto dto2 = new CreateCohortDto();
        dto2.setCourseId(courseId);
        dto2.setStartDate(LocalDate.now().plusMonths(3));
        dto2.setEndDate(LocalDate.now().plusMonths(6));

        var result2 = cohortService.createCohort(dto2);
        assertEquals(2, result2.getNumber());
    }

    @Test
    void should_ThrowException_When_CourseDoesNotExist() {
        CreateCohortDto dto = new CreateCohortDto();
        dto.setCourseId(UUID.randomUUID()); // несуществующий
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusMonths(3));

        EntityNotFoundException thrown = assertThrows(
            EntityNotFoundException.class,
            () -> cohortService.createCohort(dto)
        );

        assertTrue(thrown.getMessage().contains("не найден"));
    }

    @Test
    void should_HandleParallelCreation_WithoutNumberConflict() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        // Только одна когорта должна успешно создаться (мы не блокируем в тесте)
        // Но благодаря транзакции и блокировке — номера будут уникальны
        // Однако логически: только одна должна быть создана (если хотим уникальность)
        // Но в нашем случае: мы тестируем **нумерацию**, а не уникальность бизнес-логики

        // Просто проверим, что номера не дублируются при последовательных вызовах
        IntStream.range(0, threadCount).forEach(i -> executor.submit(() -> {
            try {
                startLatch.await(); // ждём старта

                CreateCohortDto dto = new CreateCohortDto();
                dto.setCourseId(courseId);
                dto.setStartDate(LocalDate.now().plusDays(i));
                dto.setEndDate(LocalDate.now().plusMonths(3).plusDays(i));

                var result = cohortService.createCohort(dto);
                System.out.println("Thread " + Thread.currentThread().getName() + " created cohort #" + result.getNumber());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                endLatch.countDown();
            }
        }));

        startLatch.countDown(); // стартуем все потоки
        endLatch.await(); // ждём завершения

        executor.shutdown();

        var cohorts = cohortRepository.findAllByCourseId(courseId);
    Set<Integer> actualNumbers = cohorts.stream()
        .map(c -> c.getNumber())
        .collect(Collectors.toSet());

    Set<Integer> expectedNumbers = IntStream.rangeClosed(1, threadCount)
        .boxed()
        .collect(Collectors.toSet());

    assertEquals(expectedNumbers, actualNumbers);
    }
}
