package pl.tkaras.models.mappers.impl;

import org.springframework.stereotype.Component;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.models.dto.AnswerDTO;
import pl.tkaras.models.mappers.IMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerMapper implements IMapper<Answer, AnswerDTO> {

    @Override
    public AnswerDTO mapToDto(Answer document) {
        return AnswerDTO.builder()
                .questionId(document.getQuestionId())
                .correctAnswer(document.getCorrectAnswer())
                .build();
    }

    @Override
    public Answer mapToDocument(AnswerDTO answerDTO) {
        return Answer.builder()
                .questionId(answerDTO.getQuestionId())
                .correctAnswer(answerDTO.getCorrectAnswer())
                .build();
    }

    @Override
    public List<AnswerDTO> mapToDtos(List<Answer> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Answer> mapToDocuments(List<AnswerDTO> list) {
        return list.stream()
                .map(this::mapToDocument)
                .collect(Collectors.toList());
    }
}