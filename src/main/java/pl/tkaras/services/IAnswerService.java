package pl.tkaras.services;

import pl.tkaras.models.documents.Answer;

public interface IAnswerService {

    Answer getAnswer(String id);

    Answer addAnswer(Answer answer);

    boolean checkAnswer(String email, String questionId, Integer num);

    Answer updateAnswer(String id, Answer answerDTO);

    void deleteAnswer(String id);
}