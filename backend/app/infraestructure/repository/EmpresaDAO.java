package infraestructure.repository;

import infraestructure.repository.records.EmpresaRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(EmpresaMapperDAO.class)
public interface EmpresaDAO {

    @SqlQuery("SELECT * FROM empresas")
    List<EmpresaRecord> findAll();

    @SqlQuery("SELECT * FROM empresas WHERE id = :id")
    EmpresaRecord findbyId(@Bind("id") int id);

    @SqlQuery("INSERT INTO empresas ( nombre ) VALUES ( :r.nombre ) RETURNING * ")
    EmpresaRecord insert(@BindBean("r") EmpresaRecord record);

    @SqlQuery("UPDATE empresas SET nombre = :r.nombre WHERE id = :r.id RETURNING * ")
    EmpresaRecord update(@BindBean("r") EmpresaRecord record);

    @SqlQuery("DELETE FROM empresas WHERE id = :id  RETURNING *")
    EmpresaRecord delete(@Bind("id") int id);

}
