package pl.tkaras.api.mappers.impl;

import org.springframework.stereotype.Component;
import pl.tkaras.api.documents.Question;
import pl.tkaras.api.dto.QuestionDTO;
import pl.tkaras.api.mappers.IMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements IMapper<Question, QuestionDTO> {

    @Override
    public QuestionDTO mapToDto(Question document) {
        return QuestionDTO.builder()
                .id(document.getId())
                .category(document.getCategory())
                .content(document.getContent())
                .answers(document.getAnswers())
                .correctAnswer(document.getCorrectAnswer())
                .build();
    }

    @Override
    public List<QuestionDTO> mapToDtos(List<Question> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
