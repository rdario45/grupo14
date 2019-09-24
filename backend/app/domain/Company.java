package domain;

public class Company {

    private int id;
    private String name;
    private String admin;
    private String url;

    public Company(String name, String admin, String url) {
        this.name = name;
        this.admin = admin;
        this.url = url;
    }

    public Company(int id, String name, String admin, String url) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Company cleanName() {
        // alpha and digits
        this.name = this.name.toLowerCase().replaceAll("[^a-zA-Z0-9]+","");
        return this;
    }
}
