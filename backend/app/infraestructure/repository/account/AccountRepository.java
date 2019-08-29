package infraestructure.repository.account;

import infraestructure.repository.account.records.AccountRecord;
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
}
