package infraestructure.repository;

import infraestructure.repository.records.EmpresaRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaMapperDAO implements ResultSetMapper<EmpresaRecord> {
    @Override
    public EmpresaRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new EmpresaRecord(
          r.getInt("id"),
          r.getString("nombre")
        );
    }
}
