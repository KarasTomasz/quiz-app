package pl.tkaras.api.respositories;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.tkaras.api.documents.Category;
import pl.tkaras.api.documents.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query(value="{ 'category' : ?0 }",fields="{ '_id' : 1}")
    List<String> findIdByCategory(Category category);
    List<Question> findAllByCategory(Category category);
    void deleteById(String id);
}
