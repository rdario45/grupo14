package domain;

public class Design {


    private int id;
    private String email;
    private DesignStatus designStatus;
    private String originalPath;
    private String stretchedPath;


    public Design(int id, String email, DesignStatus designStatus, String originalPath, String stretchedPath) {
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

    public void setDesignStatus(DesignStatus designStatus) {
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

    public DesignStatus getDesignStatus() {
        return designStatus;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getStretchedPath() {
        return stretchedPath;
    }

}
