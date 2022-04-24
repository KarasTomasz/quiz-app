package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.api.documents.Category;
import pl.tkaras.api.documents.Question;
import pl.tkaras.api.dto.QuestionDTO;
import pl.tkaras.api.mappers.impl.QuestionMapper;
import pl.tkaras.exceptions.QuestionNotFound;
import pl.tkaras.respositories.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;


    public List<QuestionDTO> getQuestionsByCategory(Category category) {

        return questionMapper.mapToDtos(questionRepository.findAllByCategory(category));
    }

    public List<QuestionDTO> getQuestionsByCategory(Integer number ,Category category) {

        ArrayList<Question> questionList = new ArrayList<>();

        List<String> ids = questionRepository.findIdByCategory(category);

        //TODO: add random numbers without returning
        Random rand = new Random();

        for(int i = 0; i < number; i++){

            String questionId = ids.get(rand.nextInt(ids.size()));

            questionList.add(questionRepository.findById(questionId)
                    .orElseThrow(() -> new QuestionNotFound(questionId)));
        }

        return questionMapper.mapToDtos(questionList);
    }

    public QuestionDTO getRandomQuestion(Category category) {

        List<String> idList = questionRepository.findIdByCategory(category);

        Random rand = new Random();

        String randomId = idList.get(rand.nextInt(idList.size()));

        Question question = questionRepository.findById(randomId)
                .orElseThrow(() -> new QuestionNotFound(randomId));

        return questionMapper.mapToDto(question);
    }


    public QuestionDTO addQuestion(QuestionDTO questionDTO) {

        Question question = Question.builder()
                .category(questionDTO.getCategory())
                .content(questionDTO.getContent())
                .answers(questionDTO.getAnswers())
                .correctAnswer(questionDTO.getCorrectAnswer())
                .createdAt(LocalDateTime.now())
                .build();

        return questionMapper.mapToDto(questionRepository.save(question));
    }

    public void deleteQuestion(String id) {
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
        }
        else throw new QuestionNotFound(id);
    }

    public QuestionDTO updateQuestion(String id, QuestionDTO questionDTO) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFound(id));

        question.builder()
                .category(questionDTO.getCategory())
                .content(questionDTO.getContent())
                .answers(questionDTO.getAnswers())
                .correctAnswer(questionDTO.getCorrectAnswer())
                .updatedAt(LocalDateTime.now())
                .build();

        Question returnedQuestion = questionRepository.save(question);

        return questionMapper.mapToDto(returnedQuestion);
    }
}
