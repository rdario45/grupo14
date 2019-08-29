package infraestructure.repository.project;

import infraestructure.repository.project.records.ProjectRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ProjectoMapperDAO.class)
public interface ProjectoDAO {

    @SqlQuery("SELECT * FROM projects")
    List<ProjectRecord> findAll();

    @SqlQuery("SELECT * FROM projects WHERE id = :id")
    ProjectRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO projects ( " +
      " name, " +
      " description, " +
      " cost, " +
      " company " +
      " ) VALUES ( " +
      " :r.name," +
      " :r.description," +
      " :r.cost," +
      " :r.company " +
      " ) RETURNING * ")
    ProjectRecord insert(@BindBean("r") ProjectRecord record);

    @SqlQuery("UPDATE projects SET" +
      " name = :r.name," +
      " description = :r.description, " +
      " cost = :r.cost," +
      " company = :r.company " +
      " WHERE id = :id RETURNING * ")
    ProjectRecord update(@BindBean("r") ProjectRecord record, @Bind("id") int id);

    @SqlQuery("DELETE FROM projects WHERE id = :id  RETURNING *")
    ProjectRecord delete(@Bind("id") int id);
}
