package infraestructure.repository.account.records;

public class AccountRecord {

    private String email;

    public AccountRecord(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
