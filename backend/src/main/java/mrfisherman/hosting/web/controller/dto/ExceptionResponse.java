package mrfisherman.hosting.web.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class ExceptionResponse {

    private final int statusCode;
    private final String message;
    private final Instant timestamp = Instant.now();

    public ExceptionResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode.value();
        this.message = message;
    }

}
