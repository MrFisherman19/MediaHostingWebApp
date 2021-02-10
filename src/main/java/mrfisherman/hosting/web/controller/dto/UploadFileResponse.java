package mrfisherman.hosting.web.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponse {

    private String fileId;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileId, String fileDownloadUri, long size) {
        this.fileId = fileId;
        this.fileDownloadUri = fileDownloadUri;
        this.size = size;
    }
}
