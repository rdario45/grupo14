package infraestructure.repository.project;

import infraestructure.repository.project.records.ProjectRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ProjectoMapperDAO.class)
public interface ProjectoDAO {

    @SqlQuery("SELECT * FROM proyectos")
    List<ProjectRecord> findAll();

    @SqlQuery("SELECT * FROM proyectos WHERE id = :id")
    ProjectRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO proyectos ( " +
      " nombre, " +
      " descripcion, " +
      " valor_estimado " +
      " ) VALUES ( " +
      " :r.nombre," +
      " :r.descripcion," +
      " :r.valorEstimado " +
      " ) RETURNING * ")
    ProjectRecord insert(@BindBean("r") ProjectRecord record);

    @SqlQuery("UPDATE proyectos SET" +
      " nombre = :r.nombre," +
      " descripcion = :r.descripcion, " +
      " valor_estimado = :r.valorEstimado " +
      " WHERE id = :r.id RETURNING * ")
    ProjectRecord update(@BindBean("r") ProjectRecord record);

    @SqlQuery("DELETE FROM proyectos WHERE id = :id  RETURNING *")
    ProjectRecord delete(@Bind("id") int id);
}
