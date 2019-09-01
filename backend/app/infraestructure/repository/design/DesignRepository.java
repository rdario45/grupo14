package infraestructure.repository.design;

import com.google.inject.Inject;
import domain.Design;
import infraestructure.acl.project.DesignMapper;
import io.vavr.collection.List;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class DesignRepository {
    private DBI db;

    @Inject
    public DesignRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public List<Design> findPendingAll() {
        return List.ofAll(db.onDemand(DesignDAO.class).findPendingDesigns())
                .map(DesignMapper::fromRecordToDesign);
    }

}
