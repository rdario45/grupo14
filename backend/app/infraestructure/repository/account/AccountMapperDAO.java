package infraestructure.repository.account;

import infraestructure.repository.account.records.AccountRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapperDAO implements ResultSetMapper<AccountRecord> {
    @Override
    public AccountRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new AccountRecord(
          r.getString("email")
        );
    }
}
