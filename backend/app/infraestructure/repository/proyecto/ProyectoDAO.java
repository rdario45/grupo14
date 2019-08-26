package infraestructure.repository.proyecto;

import infraestructure.repository.proyecto.records.ProyectoRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ProyectoMapperDAO.class)
public interface ProyectoDAO {

    @SqlQuery("SELECT * FROM proyectos")
    List<ProyectoRecord> findAll();

    @SqlQuery("SELECT * FROM proyectos WHERE id = :id")
    ProyectoRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO proyectos ( " +
      " nombre, " +
      " descripcion, " +
      " valor_estimado " +
      " ) VALUES ( " +
      " :r.nombre," +
      " :r.descripcion," +
      " :r.valorEstimado " +
      " ) RETURNING * ")
    ProyectoRecord insert(@BindBean("r") ProyectoRecord record);

    @SqlQuery("UPDATE proyectos SET" +
      " nombre = :r.nombre," +
      " descripcion = :r.descripcion, " +
      " valor_estimado = :r.valorEstimado " +
      " WHERE id = :r.id RETURNING * ")
    ProyectoRecord update(@BindBean("r") ProyectoRecord record);

    @SqlQuery("DELETE FROM proyectos WHERE id = :id  RETURNING *")
    ProyectoRecord delete(@Bind("id") int id);
}
