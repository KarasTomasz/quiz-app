package pl.tkaras.services;

import pl.tkaras.models.documents.Category;
import pl.tkaras.models.documents.Question;

import java.util.List;

public interface IQuestionService {

    List<Question> getQuestionsByCategory(Category category);

    List<Question> getQuestionsByCategory(Integer number ,Category category);

    Question addQuestion(Question question, int correctAnswer);

    Question updateQuestion(String id, Question question);

    void deleteQuestion(String id);
}
