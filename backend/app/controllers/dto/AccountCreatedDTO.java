package controllers.dto;

public class AccountCreatedDTO {

    private CompanyDTO company;
    private AccountDTO account;

    public AccountCreatedDTO() {
        // to json.
    }

    public AccountCreatedDTO(CompanyDTO company, AccountDTO account) {
        this.company = company;
        this.account = account;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
