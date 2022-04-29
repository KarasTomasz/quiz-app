package pl.tkaras.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.tkaras.models.documents.Answer;

import java.util.Optional;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

    boolean existsByQuestionId(String questionID);

    Optional<Answer> findByQuestionId(String questionID);

    void deleteByQuestionId(String questionId);
}