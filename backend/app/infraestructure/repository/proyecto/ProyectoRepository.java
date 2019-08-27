package infraestructure.repository.proyecto;

import com.google.inject.Inject;
import domain.Proyecto;
import infraestructure.acl.proyecto.ProyectoMapper;
import infraestructure.repository.proyecto.records.ProyectoRecord;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class ProyectoRepository {

    private DBI db;

    @Inject
    public ProyectoRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public List<Proyecto> findAll() {
        return List.ofAll(db.onDemand(ProyectoDAO.class).findAll())
          .map(ProyectoMapper::fromRecordToProyecto);
    }

    public Option<Proyecto> find(int id) {
        return Option.of(db.onDemand(ProyectoDAO.class).find(id))
          .map(ProyectoMapper::fromRecordToProyecto);
    }

    public Future<Proyecto> save(Proyecto proyecto) {
        ProyectoRecord record = ProyectoMapper.fromProyectoToRecord(proyecto);
        return Future.of(() -> db.onDemand(ProyectoDAO.class).insert(record))
          .map(ProyectoMapper::fromRecordToProyecto);
    }

    public Future<Option<Proyecto>> update(Proyecto proyecto) {
        ProyectoRecord record = ProyectoMapper.fromProyectoToRecord(proyecto);
        return Future.of(() -> Option.of(db.onDemand(ProyectoDAO.class).update(record))
          .map(ProyectoMapper::fromRecordToProyecto));
    }

    public Future<Option<Proyecto>> delete(int id) {
        return Future.of(() -> Option.of(db.onDemand(ProyectoDAO.class).delete(id))
          .map(ProyectoMapper::fromRecordToProyecto));
    }

}

