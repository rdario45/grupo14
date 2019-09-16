package controllers.dto;

import java.math.BigDecimal;

public class DesignDTO {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String folder;
    private String fileName;
    private String originalPath;
    private String resizedPath;
    private String uploadDate;
    private BigDecimal price;
    private int  projectId;

    public DesignDTO() {
        // json
    }

    public DesignDTO(int id, String email, String firstName, String lastName, String status, String folder, String fileName, String originalPath, String resizedPath, String uploadDate, BigDecimal price, int projectId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
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

    public String getStatus() {
        return status;
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

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

