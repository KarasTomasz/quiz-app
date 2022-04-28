package pl.tkaras.models.mappers.impl;

import org.springframework.stereotype.Component;
import pl.tkaras.models.documents.Question;
import pl.tkaras.models.dto.QuestionDTO;
import pl.tkaras.models.mappers.IMapper;

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
                .build();
    }

    @Override
    public Question mapToDocument(QuestionDTO questionDTO) {
        return Question.builder()
                .category(questionDTO.getCategory())
                .content(questionDTO.getContent())
                .answers(questionDTO.getAnswers())
                .build();
    }

    @Override
    public List<QuestionDTO> mapToDtos(List<Question> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> mapToDocuments(List<QuestionDTO> list) {
        return list.stream()
                .map(this::mapToDocument)
                .collect(Collectors.toList());
    }
}
