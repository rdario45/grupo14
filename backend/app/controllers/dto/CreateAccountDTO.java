package controllers.dto;

public class CreateAccountDTO {

    private String name;
    private String email;
    private String password;
    private String url;

    public CreateAccountDTO() {
        // Json
    }

    public CreateAccountDTO(String name, String email, String password, String url) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
