package infraestructure.repository.proyecto;

import infraestructure.repository.proyecto.records.ProyectoRecord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProyectoMapperDAO  implements ResultSetMapper<ProyectoRecord> {

    @Override
    public ProyectoRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ProyectoRecord(
          r.getInt("id"),
          r.getString("nombre"),
          r.getString("descripcion"),
          r.getBigDecimal("valor_estimado")
        );
    }
}
