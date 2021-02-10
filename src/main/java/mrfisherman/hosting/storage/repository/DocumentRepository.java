package mrfisherman.hosting.storage.repository;

import mrfisherman.hosting.storage.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, String> {

}
