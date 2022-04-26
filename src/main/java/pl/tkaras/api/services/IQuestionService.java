package pl.tkaras.api.services;

import pl.tkaras.api.documents.Category;
import pl.tkaras.api.documents.Question;

import java.util.List;

public interface IQuestionService {

    List<Question> getQuestionsByCategory(Category category);

    List<Question> getQuestionsByCategory(Integer number ,Category category);

    Question addQuestion(Question question);

    Question updateQuestion(String id, Question question);

    void deleteQuestion(String id);
}
