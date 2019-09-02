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

    public List<Project> findAll() {
        return List.ofAll(db.onDemand(ProjectoDAO.class).findAll())
          .map(ProjectMapper::fromRecordToProject);
    }

    public Option<Project> find(int id) {
        return Option.of(db.onDemand(ProjectoDAO.class).find(id))
          .map(ProjectMapper::fromRecordToProject);
    }

    public Future<Project> save(Project project) {
        ProjectRecord record = ProjectMapper.fromProjectToRecord(project);
        return Future.of(() -> db.onDemand(ProjectoDAO.class).insert(record))
          .map(ProjectMapper::fromRecordToProject);
    }

    public Future<Option<Project>> update(Project project, int id) {
        ProjectRecord record = ProjectMapper.fromProjectToRecord(project, id);
        return Future.of(() -> Option.of(db.onDemand(ProjectoDAO.class).update(record, id))
          .map(ProjectMapper::fromRecordToProject));

    }

    public Future<Option<Project>> delete(int id) {
        return Future.of(() -> Option.of(db.onDemand(ProjectoDAO.class).delete(id))
          .map(ProjectMapper::fromRecordToProject));
    }

}

