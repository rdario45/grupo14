package infraestructure.repository.design;

import infraestructure.repository.design.records.DesignCompanyRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DesignCompanyMapperDAO implements ResultSetMapper<DesignCompanyRecord> {

    @Override
    public DesignCompanyRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new DesignCompanyRecord(
                    r.getInt("id"),
                    r.getString("email"),
                    r.getString("firstName"),
                    r.getString("lastName"),
                    r.getString("designStatus"),
                    r.getString("fileName"),
                    r.getString("folder"),
                    r.getString("originalPath"),
                    r.getString("resizedPath"),
                    r.getTimestamp("timestamp"),
                    r.getBigDecimal("price"),
                    r.getInt("project_id"),
                    r.getInt("company_id"),
                    r.getString("name")
            );
        }




}
