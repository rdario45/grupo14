package infraestructure.repository.design;

import infraestructure.repository.design.records.DesignRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DesignMapperDAO implements ResultSetMapper<DesignRecord> {

    @Override
    public DesignRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new DesignRecord(
          r.getInt("id"),
          r.getString("email"),
          r.getString("designStatus"),
          r.getString("originalPath"),
          r.getString("resizedPath")
      	);
    }
}
