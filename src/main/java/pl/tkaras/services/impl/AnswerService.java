package pl.tkaras.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkaras.exceptions.impl.AnswerAlreadyExist;
import pl.tkaras.exceptions.impl.AnswerNotFound;
import pl.tkaras.exceptions.impl.AppUserNotFound;
import pl.tkaras.models.documents.Answer;
import pl.tkaras.models.documents.AppUser;
import pl.tkaras.respositories.AnswerRepository;
import pl.tkaras.respositories.AppUserRepository;
import pl.tkaras.services.IAnswerService;

@RequiredArgsConstructor
@Service
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;

    private final AppUserRepository appUserRepository;


    @Override
    public Answer getAnswer(String questionID) {
        return answerRepository.findByQuestionId(questionID)
                .orElseThrow(() -> new AnswerNotFound(this.getClass().getSimpleName(), questionID));
    }

    @Override
    public Answer addAnswer(Answer answer) {
        if (!answerRepository.existsByQuestionId(answer.getQuestionId())){
            return answerRepository.save(answer);
        }
        else {
            throw new AnswerAlreadyExist(this.getClass().getSimpleName(), answer.getQuestionId());
        }
    }

    @Override
    public boolean checkAnswer(String email, String questionID, Integer num) {
        Answer answer = answerRepository.findByQuestionId(questionID)
                .orElseThrow(() -> new AnswerNotFound(this.getClass().getSimpleName(), questionID));

        if(answer.getCorrectAnswer().equals(num)){
            AppUser appUser = appUserRepository.findByEmail(email)
                    .orElseThrow(() -> new AppUserNotFound(this.getClass().getSimpleName(), email));
            appUser.setScore(appUser.getScore() + 1);
            appUserRepository.save(appUser);
            return true;
        }
        return false;
    }

    @Override
    public Answer updateAnswer(String id, Answer answer) {
        Answer foundAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFound(this.getClass().getSimpleName(), id));

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
            throw new AnswerNotFound(this.getClass().getSimpleName(), id);
        }
    }
}