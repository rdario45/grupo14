package infraestructure.repository.account;

import domain.Account;
import domain.Company;
import infraestructure.acl.account.AccountMapper;
import infraestructure.repository.account.records.AccountRecord;
import infraestructure.repository.company.CompanyMapper;
import infraestructure.repository.company.records.CompanyRecord;
import infraestructure.repository.transactions.CreateCompanyAccountDAO;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

import javax.inject.Inject;

public class AccountRepository {

    private DBI db;

    @Inject
    public AccountRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public Option<AccountRecord> exists(String email) {
        return Option.of(db.onDemand(AccountDAO.class).find(email));
    }

    public Tuple2<Account, Company> createCompanyAccount(Company company, Account account) {
        return db.withHandle(handle -> {
            CreateCompanyAccountDAO dao = handle.attach(CreateCompanyAccountDAO.class);
            Account savedAccount = createAccount(account, dao);
            Company savedCompany = createCompany(company, dao);
            int companyId = getlastInsertedId(dao);
            return Tuple.of(savedAccount, savedCompany.setId(companyId));
        });
    }

    private int getlastInsertedId(CreateCompanyAccountDAO dao) {
        return dao.getLastInsertedId();
    }

    private Company createCompany(Company company, CreateCompanyAccountDAO dao) {
        CompanyRecord companyRecord = CompanyMapper.fromCompanyToRecord(company);
        dao.createCompany(companyRecord);
        return company;
    }

    private Account createAccount(Account account, CreateCompanyAccountDAO dao) {
        AccountRecord record = AccountMapper.fromAccountToRecord(account);
        dao.createAccount(record);
        return account;
    }

    public Option<Tuple2<Account, Company>> login(Account account) {
        AccountRecord record = AccountMapper.fromAccountToRecord(account);
        return Option.of(db.onDemand(AccountDAO.class).login(record))
          .map(AccountMapper::fromAccountCompanyRecordToTuple);
    }

}
