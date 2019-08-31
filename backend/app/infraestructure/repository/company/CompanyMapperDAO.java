package infraestructure.repository.company;

import infraestructure.repository.company.records.CompanyRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapperDAO implements ResultSetMapper<CompanyRecord> {

    @Override
    public CompanyRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new CompanyRecord(
          r.getInt("id"),
          r.getString("name"),
          r.getString("admin")
        );
    }
}
