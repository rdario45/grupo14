package infraestructure.repository.transactions;

import infraestructure.repository.account.AccountMapperDAO;
import infraestructure.repository.account.records.AccountRecord;
import infraestructure.repository.company.CompanyMapperDAO;
import infraestructure.repository.company.records.CompanyRecord;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

public interface CreateCompanyAccountDAO {

    @SqlUpdate("INSERT INTO accounts (" +
      "email, " +
      "password, " +
      "status" +
      ") VALUES ( " +
      ":r.email , " +
      ":r.password, " +
      ":r.status ) ")
    @RegisterMapper(AccountMapperDAO.class)
    void createAccount(@BindBean("r") AccountRecord record);

    @SqlUpdate("INSERT INTO companies (" +
      "name," +
      "admin," +
      "url " +
      ") VALUES ( " +
      ":r.name," +
      ":r.admin, " +
      ":r.url)")
    @RegisterMapper(CompanyMapperDAO.class)
    int createCompany(@BindBean("r") CompanyRecord record);

    @SqlQuery("SELECT LAST_INSERT_ID()")
    int getLastInsertedId();

    @SqlUpdate("UPDATE companies SET " +
            " name = :r.name, " +
            " admin = :r.admin, " +
            " url = :r.url " +
            " WHERE id = :r.id "
            )
    @RegisterMapper(CompanyMapperDAO.class)
    int updateCompany(@BindBean("r") CompanyRecord record);
    
}
