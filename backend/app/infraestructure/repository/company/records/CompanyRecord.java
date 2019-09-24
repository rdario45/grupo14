package infraestructure.repository.company.records;

public class CompanyRecord {

    private int id;
    private String name;
    private String admin;
    private String url;

    public CompanyRecord(int id, String name, String admin,String url) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
