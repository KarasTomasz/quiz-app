package pl.tkaras.api.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.tkaras.api.documents.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
