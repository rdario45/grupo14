package infraestructure.repository.design.records;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DesignRecord {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String designStatus;
    private String folder;
    private String fileName;
    private String originalPath;
    private String resizedPath;
    private Timestamp uploadDate;
    private BigDecimal price;
    private int projectId;

    public DesignRecord(int id, String email, String firstName, String lastName, String designStatus, String folder, String fileName, String originalPath, String resizedPath, Timestamp uploadDate, BigDecimal price, int projectId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designStatus = designStatus;
        this.folder = folder;
        this.fileName = fileName;
        this.originalPath = originalPath;
        this.resizedPath = resizedPath;
        this.uploadDate = uploadDate;
        this.price = price;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public DesignRecord setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignStatus() {
        return designStatus;
    }

    public void setDesignStatus(String designStatus) {
        this.designStatus = designStatus;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setResizedPath(String resizedPath) {
        this.resizedPath = resizedPath;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
