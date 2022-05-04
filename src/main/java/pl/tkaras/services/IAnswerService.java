package pl.tkaras.services;

import pl.tkaras.models.documents.Answer;

public interface IAnswerService {

    Answer getAnswerByQuestionId(String questionID);

    boolean checkAnswer(String email, String questionID, Integer num);

    Answer updateAnswer(String questionID, Answer answerDTO);
}