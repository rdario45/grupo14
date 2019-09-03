package controllers.dto;

public class CreateDesignDTO {

    private String fileName;
    private String filePath;
    private String email;
    private String firstName;
    private String lastName;
    private String price;
    private int projectId;

    public CreateDesignDTO() {
        // json
    }

    public CreateDesignDTO(String fileName, String filePath, String email, String firstName, String lastName, String price, int projectId) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.projectId = projectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
