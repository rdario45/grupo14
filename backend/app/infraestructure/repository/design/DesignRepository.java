package infraestructure.repository.design;

import com.google.inject.Inject;
import domain.Design;
import infraestructure.acl.design.DesignMapper;
import io.vavr.collection.List;
import io.vavr.control.Option;
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

    public List<Design> findAll() {
        return List.ofAll(db.onDemand(DesignDAO.class).findAll())
                .map(DesignMapper::fromRecordToDesign);
    }

    public Option<Design> find(int id) {
        return Option.of(db.onDemand(DesignDAO.class).find(id))
                .map(DesignMapper::fromRecordToDesign);
    }


}
