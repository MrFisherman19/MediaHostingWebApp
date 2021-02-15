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

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


}
