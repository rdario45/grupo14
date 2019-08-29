package controllers.dto;

public class ProjectDTO {

    private int id;
    private String name;
    private String description;
    private String cost;
    private int company;

    public ProjectDTO() {
        // to json mapper
    }

    public ProjectDTO(int id, String name, String description, String cost, int company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.company = company;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }
}
