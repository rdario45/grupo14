package infraestructure.repository.account;

import infraestructure.repository.account.records.AccountCompanyRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCompanyMapperDAO implements ResultSetMapper<AccountCompanyRecord> {
    @Override
    public AccountCompanyRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new AccountCompanyRecord(
          r.getInt("companyId"),
          r.getString("companyName"),
          r.getString("adminEmail"),
          r.getString("adminStatus"),
          r.getString("companyUrl")
        );
    }
}
