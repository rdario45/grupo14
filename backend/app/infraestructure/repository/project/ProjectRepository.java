package infraestructure.repository.project;

import com.google.inject.Inject;
import domain.Project;
import infraestructure.acl.project.ProjectMapper;
import infraestructure.repository.project.records.ProjectRecord;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class ProjectRepository {

    private DBI db;

    @Inject
    public ProjectRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public Option<Project> find(int id) {
        return Option.of(db.onDemand(ProjectoDAO.class).find(id))
          .map(ProjectMapper::fromRecordToProject);
    }

    public List<Project> findByCompanyPaginated(int id, int offset, int limit) {
        return List.ofAll(db.onDemand(ProjectoDAO.class).findByCompany(id, offset, limit))
          .map(ProjectMapper::fromRecordToProject);
    }

    public Future<Project> save(Project project) {
        ProjectRecord record = ProjectMapper.fromProjectToRecord(project);
        return Future.of(() -> {
            db.onDemand(ProjectoDAO.class).insert(record);
            int lastInsertedId = db.onDemand(ProjectoDAO.class).getLastInsertedId();
            return record.setId(lastInsertedId);
        }).map(ProjectMapper::fromRecordToProject);
    }

    public Future<Option<Project>> update(Project project, int id) {
        ProjectRecord record = ProjectMapper.fromProjectToRecord(project, id);

        return Future.of(() -> {
            int rows = db.onDemand(ProjectoDAO.class).update(record, id);
            return rows > 0 ? Option.some(record).map(ProjectMapper::fromRecordToProject) : Option.none();
        });

    }

    public Future<Option<Integer>> delete(int id) {
        return Future.of(() -> {
            int rows = db.onDemand(ProjectoDAO.class).delete(id);
            return rows > 0 ? Option.some(id) : Option.none();
        });
    }

}

