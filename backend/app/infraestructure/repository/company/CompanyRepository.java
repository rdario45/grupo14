package infraestructure.repository.company;

import infraestructure.repository.company.records.CompanyRecord;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

import javax.inject.Inject;

public class CompanyRepository {

    private DBI db;

    @Inject
    public CompanyRepository(Database db) {
        this.db = new DBI(db.dataSource());
    }

    public Option<CompanyRecord> getCompanyByDesignId(int designId) {
        return Option.of(db.onDemand(CompanyDAO.class).findByDesignId(designId));
    }
}