package pl.tkaras.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.tkaras.api.documents.Category;
import pl.tkaras.api.documents.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findById(String s);
    List<String> findIdByCategory(Category category);
    List<Question> findAllByCategory(Category category);

    @Query("SELECT q FROM question q WHERE q.category = ?2 Limit ?1")
    List<Question> findByCategory(Integer amount, Category category);

    Question save(Question question);
    void deleteById(String id);
}
