package mrfisherman.hosting.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrfisherman.hosting.storage.model.Document;
import mrfisherman.hosting.storage.repository.DocumentRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MongoStoringService implements StoringService {

    private final DocumentRepository documentRepository;

    @Override
    public Document loadFileByUsernameAndFileId(String username, String fileId) throws FileNotFoundException {
        return documentRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File with given id cannot be found!"));
    }

    @Override
    public List<Document> getFilesMetadataForUser(String username) {
        return documentRepository.findDocumentsMetadataByUsername(username);
    }

    @Override
    public List<Document> storeFiles(MultipartFile[] files, String username) {
        return Arrays.stream(files)
                .map(file -> createDocument(file, username))
                .collect(Collectors.toList());
    }

    private Document createDocument(MultipartFile file, String username) {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setUsername(username);
        document.setContentType(file.getContentType());
        document.setSize(file.getSize());
        document.setCreated(new Date());
        try {
            document.setBinary(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return documentRepository.save(document);
    }


}
