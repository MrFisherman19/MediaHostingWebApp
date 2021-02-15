package mrfisherman.hosting.storage.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {

    @Id
    private String fileId;
    private Binary binary;
    private String username;
    private String contentType;
    private String fileName;
    private long size;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date created = new Date();
}
