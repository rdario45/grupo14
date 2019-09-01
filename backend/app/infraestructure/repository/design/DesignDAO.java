package infraestructure.repository.design;

import infraestructure.repository.design.records.DesignRecord;
import infraestructure.repository.project.records.ProjectRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(DesignMapperDAO.class)
public interface DesignDAO {


    @SqlQuery("SELECT * FROM Design where designStatus = 'PROCESSING'")
    List<DesignRecord> findPendingDesigns();

    @SqlQuery("SELECT * FROM Design")
    List<ProjectRecord> findAll();

    @SqlQuery("SELECT * FROM Design WHERE id = :id")
    DesignRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO Design ( " +
            " id, " +
            " mail, " +
            " stretched, " +
            " originalPath, " +
            " stretchedPath, " +
            " ) VALUES ( " +
            " :r.id," +
            " :r.mail," +
            " :r.stretched," +
            " :r.originalPath " +
            " :r.stretchedPath " +
            " ) RETURNING * ")
    DesignRecord insert(@BindBean("r") DesignRecord record);

    @SqlQuery("UPDATE design SET" +
            " mail = :r.mail," +
            " stretched = :r.stretched, " +
            " originalPath = :r.originalPath," +
            " stretchedPath = :r.stretchedPath " +
            " WHERE id = :id RETURNING * ")
    DesignRecord update(@BindBean("r") DesignRecord record, @Bind("id") int id);

    @SqlQuery("DELETE FROM Design WHERE id = :id  RETURNING *")
    DesignRecord delete(@Bind("id") int id);



}
