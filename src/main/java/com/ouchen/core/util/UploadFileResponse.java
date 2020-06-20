package com.ouchen.core.util;

/**
 * 文件下载响应类 ClassName:UploadFileResponse
 *
 * @author ligy-008494
 * @create 2019-09-19 17:31
 */
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    // Getters and Setters (Omitted for brevity)
}

