package pl.tkaras.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.tkaras.api.documents.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

}
