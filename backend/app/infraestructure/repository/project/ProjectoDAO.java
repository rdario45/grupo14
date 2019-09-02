package infraestructure.repository.project;

import infraestructure.repository.project.records.ProjectRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ProjectoMapperDAO.class)
public interface ProjectoDAO {

    @SqlQuery("SELECT * FROM projects")
    List<ProjectRecord> findAll();

    @SqlQuery("SELECT * FROM projects WHERE id = :id")
    ProjectRecord find(@Bind("id") int id);

    @SqlUpdate("INSERT INTO projects ( " +
      " name, " +
      " description, " +
      " cost, " +
      " company_id " +
      " ) VALUES ( " +
      " :r.name," +
      " :r.description," +
      " :r.cost," +
      " :r.companyId " +
      " ) ")
    int insert(@BindBean("r") ProjectRecord record);

    @SqlUpdate("UPDATE projects SET" +
      " name = :r.name," +
      " description = :r.description, " +
      " cost = :r.cost," +
      " company_id = :r.companyId " +
      " WHERE id = :id ")
    int update(@BindBean("r") ProjectRecord record, @Bind("id") int id);

    @SqlUpdate("DELETE FROM projects WHERE id = :id ")
    int delete(@Bind("id") int id);
}
