package mrfisherman.hosting.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadFileResponse {

    private String fileId;
    private String fileName;
    private String fileDownloadUri;
    private String contentType;
    private Date created;
    private long size;

    public UploadFileResponse(String fileId, String fileName, String fileDownloadUri, String contentType, long size, Date created) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.contentType = contentType;
        this.size = size;
        this.created = created;
    }
}
