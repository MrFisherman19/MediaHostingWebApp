package mrfisherman.hosting.storage.service;

import mrfisherman.hosting.storage.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface StoringService {

    String storeFile(String fileName, String username, MultipartFile file) throws IOException;

    Document loadFileByUsernameAndFileId(String username, String fileId) throws FileNotFoundException;

}
