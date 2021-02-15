package mrfisherman.hosting.web.controller;

import lombok.RequiredArgsConstructor;
import mrfisherman.hosting.storage.model.Document;
import mrfisherman.hosting.storage.service.StoringService;
import mrfisherman.hosting.web.controller.dto.ExceptionResponse;
import mrfisherman.hosting.web.controller.dto.UploadFileResponse;
import mrfisherman.hosting.web.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StoringService storingService;

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("filename") String fileName, @RequestParam("file") MultipartFile file,
                                         @AuthenticationPrincipal User user) throws IOException {

        Document fileAfterSaving = storingService.storeFile(fileName, user.getUsername(), file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .queryParam("fileId", fileAfterSaving.getFileId())
                .toUriString();
        return new UploadFileResponse(fileAfterSaving.getFileId(), fileDownloadUri,
                fileAfterSaving.getContentType(), fileAfterSaving.getSize(), fileAfterSaving.getCreated());
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@AuthenticationPrincipal User user,
                                             @RequestParam("fileId") String fileId) throws FileNotFoundException {

        System.out.println(user.getUsername());

        Document file = storingService.loadFileByUsernameAndFileId(user.getUsername(), fileId);
        Resource resource = new ByteArrayResource(file.getBinary().getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .body(resource);
    }

    @GetMapping("/files")
    private List<Document> getDocumentsMetadataByUser(@AuthenticationPrincipal User user) {
        return storingService.getFilesMetadataForUser(user.getUsername());
    }

    @ExceptionHandler(value = IOException.class)
    public ExceptionResponse ioExceptionHandler(Exception e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());

    }

}
