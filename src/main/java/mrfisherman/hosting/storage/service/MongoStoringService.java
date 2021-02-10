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

@Service
@RequiredArgsConstructor
public class MongoStoringService implements StoringService {

    private final DocumentRepository documentRepository;

    @Override
    public String storeFile(String fileName, String username, MultipartFile file) throws IOException {
       Document document = new Document();
       document.setFileName(fileName);
       document.setUsername(username);
       document.setContentType(file.getContentType());
       document.setBinary(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
       return documentRepository.save(document).getFileId();
    }

    @Override
    public Document loadFileByUsernameAndFileId(String username, String fileId) throws FileNotFoundException {
        //TODO username
        return documentRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File with given id cannot be found!"));
    }
}
