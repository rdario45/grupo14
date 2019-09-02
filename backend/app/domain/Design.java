package domain;

import java.util.Date;

public class Design {


    private int id;
    private String email;
    private DesignStatus designStatus;
    private String originalPath;
    private String resizedPath;
    private Date uploadDate;
    private int projectId;


    public Design(int id, String email, DesignStatus designStatus, String originalPath, String resizedPath, Date uploadDate, int projectId) {
        this.id = id;
        this.email = email;
        this.designStatus = designStatus;
        this.originalPath = originalPath;
        this.resizedPath = resizedPath;
        this.uploadDate = uploadDate;
        this.projectId = projectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDesignStatus(DesignStatus designStatus) {
        this.designStatus = designStatus;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public void setStretchedPath(String resizedPath) {
        this.resizedPath = resizedPath;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public DesignStatus getDesignStatus() {
        return designStatus;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getResizedPath() {
        return resizedPath;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setResizedPath(String resizedPath) {
        this.resizedPath = resizedPath;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
