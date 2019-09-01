package infraestructure.repository.design.records;

public class DesignRecord {

    private int id;
    private String email;
    private String designStatus;
    private String originalPath;
    private String resizedPath;

    public DesignRecord(int id, String email, String designStatus, String originalPath, String resizedPath) {
        this.id = id;
        this.email = email;
        this.designStatus = designStatus;
        this.originalPath = originalPath;
        this.resizedPath = resizedPath;
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

    public String getResizedPath() {
        return resizedPath;
    }

    public void setResizedPath(String resizedPath) {
        this.resizedPath = resizedPath;
    }
}
