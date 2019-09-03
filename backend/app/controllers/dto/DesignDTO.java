package controllers.dto;

public class DesignDTO {

    private int id;
    private String email;
    private String designStatus;
    private String originalPath;
    private String stretchedPath;
    private String uploadDate;
    private int  projectId;

    public DesignDTO() {
        // json
    }

    public DesignDTO(int id, String email, String designStatus, String originalPath, String stretchedPath, String uploadDate, int projectId) {
        this.id = id;
        this.email = email;
        this.designStatus = designStatus;
        this.originalPath = originalPath;
        this.stretchedPath = stretchedPath;
        this.uploadDate = uploadDate;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignStatus() {
        return designStatus;
    }

    public void setDesignStatus(String designStatus) {
        this.designStatus = designStatus;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getStretchedPath() {
        return stretchedPath;
    }

    public void setStretchedPath(String stretchedPath) {
        this.stretchedPath = stretchedPath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

