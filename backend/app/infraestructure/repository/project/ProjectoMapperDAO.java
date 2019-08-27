package infraestructure.repository.project;

import infraestructure.repository.project.records.ProjectRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectoMapperDAO implements ResultSetMapper<ProjectRecord> {

    @Override
    public ProjectRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ProjectRecord(
          r.getInt("id"),
          r.getString("nombre"),
          r.getString("descripcion"),
          r.getBigDecimal("valor_estimado")
        );
    }
}
