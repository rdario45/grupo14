package infraestructure.repository.design.records;

import java.math.BigDecimal;

public class DesignRecord {

    private int id;
    private String email;
    private Boolean stretched;
    private String originalPath;
    private String stretchedPath;


    public DesignRecord(int id, String email, Boolean stretched, String originalPath, String stretchedPath) {
        this.id = id;
        this.email = email;
        this.stretched = stretched;
        this.originalPath = originalPath;
        this.stretchedPath = stretchedPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStretched(Boolean stretched) {
        this.stretched = stretched;
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

    public Boolean getStretched() {
        return stretched;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getStretchedPath() {
        return stretchedPath;
    }
}
