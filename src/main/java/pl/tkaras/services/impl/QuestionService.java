package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tkaras.exceptions.impl.AnswerAlreadyExist;
import pl.tkaras.exceptions.impl.AnswerNotFound;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.models.documents.Category;
import pl.tkaras.models.documents.Question;
import pl.tkaras.respositories.AnswerRepository;
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
    private final AnswerRepository answerRepository;
    
    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    public List<Question> getQuestionsByCategory(Integer number ,Category category) {

        List<Question> questions = questionRepository.findAllByCategory(category);

        if (questions.size() < number){
            throw new QuestionNotEnough(this.getClass().getSimpleName(), "questions");
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
    public Question addQuestion(Question question, int correctAnswer) {

        question.setCreatedAt(LocalDateTime.now());

        //add question
        Question savedQuestion =  questionRepository.save(question);

        Answer answer = Answer.builder()
                .questionId(savedQuestion.getId())
                .correctAnswer(correctAnswer)
                .build();

        //add answer
        if (!answerRepository.existsByQuestionId(savedQuestion.getId())){
               answerRepository.save(answer);
        }
        else {
            throw new AnswerAlreadyExist(this.getClass().getSimpleName(), answer.getQuestionId());
        }

        return savedQuestion;
    }

    public Question updateQuestion(String id, Question question) {

        Question foundQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFound(this.getClass().getSimpleName(), id));

        foundQuestion.setCategory(question.getCategory());
        foundQuestion.setContent(question.getContent());
        foundQuestion.setAnswers(question.getAnswers());
        foundQuestion.setCorrectAnswer(question.getCorrectAnswer());
        foundQuestion.setUpdatedAt(LocalDateTime.now());

        return questionRepository.save(foundQuestion);
    }

    @Transactional
    public void deleteQuestion(String id) {

        Question returnedQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFound(this.getClass().getSimpleName(), id));

        if (answerRepository.existsByQuestionId(returnedQuestion.getId())){
            //delete answer
            answerRepository.deleteByQuestionId(returnedQuestion.getId());

            //delete question
            questionRepository.deleteById(id);
        }
        else {
            throw new AnswerNotFound(this.getClass().getSimpleName(), "answer");
        }
    }
}
