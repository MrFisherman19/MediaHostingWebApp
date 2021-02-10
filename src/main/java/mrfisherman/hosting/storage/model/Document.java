package mrfisherman.hosting.storage.model;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Document {

    @Id
    private String fileId;
    private Binary binary;
    private String username;
    private String contentType;
    private String fileName;
}
