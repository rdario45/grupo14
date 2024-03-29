package infraestructure.repository.project.records;

import java.math.BigDecimal;

public class ProjectRecord {

    private int id;
    private String name;
    private String description;
    private BigDecimal cost;
    private int companyId;

    public ProjectRecord(int id, String name, String description, BigDecimal cost, int companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public ProjectRecord setId(int id) {
        this.id = id;
        return this;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
