package pl.tkaras.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.tkaras.models.documents.Category;
import pl.tkaras.models.documents.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query(value="{ 'category' : ?0 }",fields="{ '_id' : 1}")
    List<String> findIdByCategory(Category category);
    List<Question> findAllByCategory(Category category);
    void deleteById(String id);
}
