package infraestructure.repository.design.records;

import java.math.BigDecimal;

public class DesignRecord {

    private int id;
    private String email;
    private String designStatus;
    private String originalPath;
    private String stretchedPath;


    public DesignRecord(int id, String email, String designStatus, String originalPath, String stretchedPath) {
        this.id = id;
        this.email = email;
        this.designStatus = designStatus;
        this.originalPath = originalPath;
        this.stretchedPath = stretchedPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDesignStatus(String designStatus) {
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
}
