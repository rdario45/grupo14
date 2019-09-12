package infraestructure.repository.design;

import infraestructure.repository.design.records.DesignCompanyRecord;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(DesignCompanyMapperDAO.class)
public interface DesignCompanyDAO {

    @SqlQuery("select a.*, c.id as company_id, c.name from designs a, projects b, companies c " +
            "    where a.project_id = b.id  and b.company_id = c.id and a.designStatus = 'PROCESSING' ")
    List<DesignCompanyRecord> findPendingDesigns();


}
