package mrfisherman.hosting.storage.service;

import lombok.RequiredArgsConstructor;
import mrfisherman.hosting.storage.model.Document;
import mrfisherman.hosting.storage.repository.DocumentRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoStoringService implements StoringService {

    private final DocumentRepository documentRepository;

    @Override
    public Document storeFile(String fileName, String username, MultipartFile file) throws IOException {
       Document document = new Document();
       document.setFileName(fileName);
       document.setUsername(username);
       document.setContentType(file.getContentType());
       document.setSize(file.getSize());
       document.setBinary(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
       return documentRepository.save(document);
    }

    @Override
    public Document loadFileByUsernameAndFileId(String username, String fileId) throws FileNotFoundException {
        return documentRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File with given id cannot be found!"));
    }

    @Override
    public List<Document> getFilesMetadataForUser(String username) {
        return documentRepository.findDocumentsMetadataByUsername(username);
    }


}
