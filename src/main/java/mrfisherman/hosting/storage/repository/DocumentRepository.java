package mrfisherman.hosting.storage.repository;

import mrfisherman.hosting.storage.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<Document, String> {

    @Query(value = "{ 'username' : ?0 }", fields = "{ 'fileId' : 1, 'contentType' : 1, 'fileName': 1, 'size' : 1 }")
    List<Document> findDocumentsMetadataByUsername(String username);

}
