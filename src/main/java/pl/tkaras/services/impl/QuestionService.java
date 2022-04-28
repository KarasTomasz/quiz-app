package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.models.documents.Category;
import pl.tkaras.models.documents.Question;
import pl.tkaras.services.IQuestionService;
import pl.tkaras.exceptions.impl.QuestionNotEnough;
import pl.tkaras.exceptions.impl.QuestionNotFound;
import pl.tkaras.respositories.QuestionRepository;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class QuestionService  implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    
    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    public List<Question> getQuestionsByCategory(Integer number ,Category category) {

        List<Question> questions = questionRepository.findAllByCategory(category);

        if (questions.size() < number){
            throw new QuestionNotEnough("questions");
        }
        else{

            Set<Question> questionSet = new HashSet<>();
            Random rand = new Random();

            while(!(questionSet.size() == number)){
                questionSet.add(questions.get(rand.nextInt(questions.size())));
            }

            return new ArrayList<>(questionSet);
        }
    }

    @Transactional
    public Question addQuestion(Question question) {
        question.setCreatedAt(LocalDateTime.now());

        Answer answer = Answer.builder()
                .questionId(question.getId())
                .correctAnswer(question.getCorrectAnswer())
                .build();

        answerService.addAnswer(answer);

        return questionRepository.save(question);
    }

    public Question updateQuestion(String id, Question question) {

        Question foundQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFound(id));

        foundQuestion.setCategory(question.getCategory());
        foundQuestion.setContent(question.getContent());
        foundQuestion.setAnswers(question.getAnswers());
        foundQuestion.setCorrectAnswer(question.getCorrectAnswer());
        foundQuestion.setUpdatedAt(LocalDateTime.now());

        return questionRepository.save(foundQuestion);
    }

    public void deleteQuestion(String id) {
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
        }
        else throw new QuestionNotFound(id);
    }

}
