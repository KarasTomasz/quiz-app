package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.exceptions.impl.AnswerAlreadyExist;
import pl.tkaras.exceptions.impl.AnswerNotFound;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.respositories.AnswerRepository;
import pl.tkaras.services.IAnswerService;

@RequiredArgsConstructor
@Service
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;


    @Override
    public Answer getAnswer(String questionID) {
        return answerRepository.findByQuestionId(questionID)
                .orElseThrow(() -> new AnswerNotFound(questionID));
    }

    @Override
    public Answer addAnswer(Answer answer) {
        if (!answerRepository.existsByQuestionId(answer.getQuestionId())){
            return answerRepository.save(answer);
        }
        else {
            throw new AnswerAlreadyExist(answer.getQuestionId());
        }
    }

    @Override
    public boolean checkAnswer(String questionID, Integer num) {
        Answer answer = answerRepository.findByQuestionId(questionID)
                .orElseThrow(() -> new AnswerNotFound(questionID));

        return answer.getCorrectAnswer().equals(num);
    }

    @Override
    public Answer updateAnswer(String id, Answer answer) {
        Answer foundAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFound(id));

        foundAnswer.setQuestionId(answer.getQuestionId());
        foundAnswer.setCorrectAnswer(answer.getCorrectAnswer());
        return answerRepository.save(foundAnswer);
    }

    @Override
    public void deleteAnswer(String id) {
        if(answerRepository.existsById(id)){
            answerRepository.deleteById(id);
        }
        else {
            throw new AnswerNotFound(id);
        }
    }
}