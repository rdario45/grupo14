package infraestructure.repository.design;

import domain.DesignStatus;
import infraestructure.repository.design.records.DesignRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(DesignMapperDAO.class)
public interface DesignDAO {

    @SqlQuery("SELECT * FROM designs where designStatus = 'PROCESSING'")
    List<DesignRecord> findPendingDesigns();

    @SqlQuery("SELECT * FROM designs WHERE project_id = :projectId ")
    List<DesignRecord> findByProject(@Bind("projectId") int projectId);

    @SqlQuery("SELECT * FROM designs WHERE project_id = :projectId AND designStatus = :status ")
    List<DesignRecord> findByProjectAndStatus(@Bind("projectId") int projectId, @Bind("status")  DesignStatus status);

    @SqlQuery("SELECT * FROM designs WHERE id = :id")
    DesignRecord find(@Bind("id") int id);

    @SqlUpdate("INSERT INTO designs ( " +
      " email, " +
      " firstName, " +
      " lastName," +
      " designStatus, " +
      " fileName, " +
      " originalPath, " +
      " resizedPath, " +
      " price, " +
      " project_id " +
      " ) VALUES ( " +
      " :r.email, " +
      " :r.firstName, " +
      " :r.lastName, " +
      " :r.designStatus, " +
      " :r.fileName, " +
      " :r.originalPath, " +
      " :r.resizedPath, " +
      " :r.price," +
      " :r.projectId " +
      " ) ")
    int create(@BindBean("r") DesignRecord record);

    @SqlUpdate("UPDATE designs SET " +
      " email = :r.email, " +
      " firstName = :r.firstName, " +
      " lastName = :r.lastName, " +
      " designStatus = :r.designStatus," +
      " fileName = :r.fileName,  " +
      " originalPath = :r.originalPath, " +
      " resizedPath = :r.resizedPath, " +
      " price = :r.price " +
      " WHERE id = :r.id ")
    int update(@BindBean("r") DesignRecord record);

    @SqlUpdate("DELETE FROM Design WHERE id = :id ")
    int delete(@Bind("id") int id);

}
