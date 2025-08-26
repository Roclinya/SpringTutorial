package com.tutorial.SpringTutorial.vo;

public class ReportServiceResponse {
    private Integer status = 0;
    private String fileId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
