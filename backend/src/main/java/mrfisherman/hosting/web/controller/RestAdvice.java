package mrfisherman.hosting.web.controller;

import mrfisherman.hosting.web.controller.dto.ExceptionResponse;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleSizeLimitExceededException(SizeLimitExceededException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}
