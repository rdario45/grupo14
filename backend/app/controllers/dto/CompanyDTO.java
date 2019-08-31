package controllers.dto;

public class CompanyDTO {

    private int id;
    private String name;
    private String admin;

    public CompanyDTO() {
        // to json
    }

    public CompanyDTO(int id, String name, String admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
