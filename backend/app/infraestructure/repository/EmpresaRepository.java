package infraestructure.repository;

import com.google.inject.Inject;
import domain.Empresa;
import infraestructure.acl.EmpresaMapper;
import infraestructure.repository.records.EmpresaRecord;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class EmpresaRepository {

    private DBI db;

    @Inject
    public EmpresaRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public List<Empresa> findAll() {
        return List.ofAll(db.onDemand(EmpresaDAO.class).findAll())
          .map(EmpresaMapper::fromRecordToEmpresa);
    }

    public Option<Empresa> find(int id) {
        return Option.of(db.onDemand(EmpresaDAO.class).findbyId(id))
          .map(EmpresaMapper::fromRecordToEmpresa);
    }

    public Future<Empresa> save(Empresa empresa) {
        EmpresaRecord record = EmpresaMapper.fromEmpresaToRecord(empresa);
        return Future.of(() -> db.onDemand(EmpresaDAO.class).insert(record))
          .map(EmpresaMapper::fromRecordToEmpresa);
    }

    public Future<Option<Empresa>> update(Empresa empresa) {
        EmpresaRecord record = EmpresaMapper.fromEmpresaToRecord(empresa);
        return Future.of(() -> Option.of(db.onDemand(EmpresaDAO.class).update(record))
          .map(EmpresaMapper::fromRecordToEmpresa));
    }

    public Future<Option<Empresa>> delete(int id) {
        return Future.of(() -> Option.of(db.onDemand(EmpresaDAO.class).delete(id))
          .map(EmpresaMapper::fromRecordToEmpresa));
    }

}
