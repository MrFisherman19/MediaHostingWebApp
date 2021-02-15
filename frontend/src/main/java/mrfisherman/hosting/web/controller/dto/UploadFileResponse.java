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
    private String fileDownloadUri;
    private String fileType;
    private Date created;
    private long size;

    public UploadFileResponse(String fileId, String fileDownloadUri, String fileType, long size,  Date created) {
        this.fileId = fileId;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.created = created;
    }
}
