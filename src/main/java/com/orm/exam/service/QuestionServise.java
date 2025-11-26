package com.orm.exam.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orm.exam.dto.QuestionDto;
import com.orm.exam.dto.create.CreateQuestionDto;
import com.orm.exam.entity.AnswerOptionEntity;
import com.orm.exam.entity.QuestionEntity;
import com.orm.exam.exception.EntityNotFoundException;
import com.orm.exam.mapper.AnswerOptionMapper;
import com.orm.exam.mapper.QuestionMapper;
import com.orm.exam.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionServise {
    private AnswerOptionMapper answerOptionMapper;
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public QuestionDto getQuestionById(UUID id) {
        QuestionEntity entity = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Вопрос с идентификатором " + id + " не найден."));
        return questionMapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream().map(questionMapper::toDto).toList();
    }

    @Transactional
    public QuestionDto createQuestion(CreateQuestionDto dto) {
        QuestionEntity question = questionMapper.prepareEntityForCreate(dto);

        dto.getAnswerOptions().stream()
                .map(answerDto -> {
                        AnswerOptionEntity answer = answerOptionMapper.prepareEntityForCreate(answerDto);
                    answer.setQuestion(question);
                    return answer;
                })
                .forEach(question::addAnswer);

        QuestionEntity savedQuestion = questionRepository.save(question);
        return questionMapper.toDto(savedQuestion);
    }

    @Transactional
    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);
    }

}
