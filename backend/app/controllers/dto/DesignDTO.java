package controllers.dto;

public class DesignDTO {

    private int id;
    private String email;
    private String designStatus;
    private String originalPath;
    private String stretchedPath;
    private int  projectId;



    public DesignDTO(){

    }

    public DesignDTO(int id, String email, String designStatus, String originalPath, String stretchedPath, int projectId) {
        this.id = id;
        this.email = email;
        this.designStatus = designStatus;
        this.originalPath = originalPath;
        this.stretchedPath = stretchedPath;
        this.projectId = projectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStretched(String designStatus) {
        this.designStatus = designStatus;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public void setStretchedPath(String stretchedPath) {
        this.stretchedPath = stretchedPath;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDesignStatus() {
        return designStatus;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getStretchedPath() {
        return stretchedPath;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setDesignStatus(String designStatus) {
        this.designStatus = designStatus;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

