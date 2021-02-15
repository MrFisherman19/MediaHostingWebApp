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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final StoringService storingService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadFileResponse> uploadFile(@RequestParam MultipartFile[] files,
                                         @AuthenticationPrincipal User user) throws IOException {

        List<Document> fileAfterSaving = storingService.storeFiles(files, user.getUsername());
        return fileAfterSaving.stream()
                .map(this::mapToUploadResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@AuthenticationPrincipal User user,
                                             @RequestParam("fileId") String fileId) throws FileNotFoundException {

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

    private UploadFileResponse mapToUploadResponse(Document file) {
        return new UploadFileResponse(
                file.getFileId(),
                file.getFileName(),
                getDownloadUriForFileById(file.getFileId()),
                file.getContentType(),
                file.getSize(),
                file.getCreated());
    }

    private String getDownloadUriForFileById(String fileId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .queryParam("fileId", fileId)
                .toUriString();
    }
}
