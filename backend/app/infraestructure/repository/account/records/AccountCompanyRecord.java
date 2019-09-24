package infraestructure.repository.account.records;

public class AccountCompanyRecord {

    private int companyId;
    private String companyName;
    private String adminEmail;
    private String adminStatus;
    private String url;

    public AccountCompanyRecord(int companyId, String companyName, String adminEmail, String adminStatus, String url) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.adminEmail = adminEmail;
        this.adminStatus = adminStatus;
        this.url = url;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
