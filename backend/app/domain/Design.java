package domain;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Design {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private DesignStatus designStatus;
    private String fileName;
    private String originalPath;
    private String resizedPath;
    private DateTime uploadDate;
    private BigDecimal price;
    private int projectId;

    public Design(String email, String firstName, String lastName, DesignStatus designStatus, String fileName, String originalPath, DateTime uploadDate, BigDecimal price, int projectId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designStatus = designStatus;
        this.fileName = fileName;
        this.originalPath = originalPath;
        this.uploadDate = uploadDate;
        this.price = price;
        this.projectId = projectId;
    }

    public Design(int id, String email, String firstName, String lastName, DesignStatus designStatus, String fileName, String originalPath, String resizedPath, DateTime uploadDate, BigDecimal price, int projectId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designStatus = designStatus;
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

    public void setId(int id) {
        this.id = id;
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

    public DesignStatus getDesignStatus() {
        return designStatus;
    }

    public void setDesignStatus(DesignStatus designStatus) {
        this.designStatus = designStatus;
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

    public void setResizedPath(String resizedPath) {
        this.resizedPath = resizedPath;
    }

    public DateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(DateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
