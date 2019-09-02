package infraestructure.repository.design;

import infraestructure.repository.design.records.DesignRecord;
import infraestructure.repository.project.records.ProjectRecord;
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

    @SqlQuery("SELECT * FROM designs ")
    List<ProjectRecord> findAll();

    @SqlQuery("SELECT * FROM designs WHERE id = :id")
    DesignRecord find(@Bind("id") int id);

    @SqlUpdate("INSERT INTO designs ( " +
            " id, " +
            " email, " +
            " designStatus, " +
            " originalPath, " +
            " resizedPath, " +
            " ) VALUES ( " +
            " :r.id," +
            " :r.mail," +
            " :r.stretched," +
            " :r.originalPath " +
            " :r.resizedPath " +
            " ) ")
    int insert(@BindBean("r") DesignRecord record);

    @SqlUpdate("UPDATE designs SET" +
            " email = :r.email," +
            " designStatus = :r.designStatus, " +
            " originalPath = :r.originalPath," +
            " resizedPath = :r.resizedPath " +
            " WHERE id = :r.id ")
    int update(@BindBean("r") DesignRecord record);

    @SqlUpdate("DELETE FROM Design WHERE id = :id ")
    int delete(@Bind("id") int id);



}
