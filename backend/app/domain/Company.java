package domain;

public class Company {

    private int id;
    private String name;
    private String admin;

    public Company(String name, String admin) {
        this.name = name;
        this.admin = admin;
    }

    public Company(int id, String name, String admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public Company setId(int id) {
        this.id = id;
        return this;
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
