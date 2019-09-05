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

    @SqlQuery("SELECT * FROM projects WHERE id = :id")
    ProjectRecord find(@Bind("id") int id);

    @SqlQuery("SELECT * FROM projects WHERE company_id = :companyId ORDER BY timestamp DESC LIMIT :offset, :limit ")
    List<ProjectRecord> findByCompany(@Bind("companyId") int companyId, @Bind("offset") int offset, @Bind("limit") int limit);

    @SqlQuery("SELECT * FROM projects WHERE company_id = :companyId ")
    int count(@Bind("companyId") int companyId);

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
    void insert(@BindBean("r") ProjectRecord record);

    @SqlQuery("SELECT LAST_INSERT_ID()")
    int getLastInsertedId();

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
