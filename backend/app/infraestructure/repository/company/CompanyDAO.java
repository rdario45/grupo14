
package infraestructure.repository.company;

import infraestructure.repository.company.records.CompanyRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(CompanyMapperDAO.class)
public interface CompanyDAO {

    @SqlQuery("SELECT c.* " +
            "FROM companies c, " +
            "projects p, " +
            "designs d " +
            "WHERE d.project_id = p.id " +
            "AND p.company_id = c.id " +
            "AND d.id = :designId ")
    CompanyRecord findByDesignId(@Bind("designId") int designId);

}