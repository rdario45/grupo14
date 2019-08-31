package infraestructure.repository.transactions;

import infraestructure.repository.account.AccountMapperDAO;
import infraestructure.repository.account.records.AccountRecord;
import infraestructure.repository.company.CompanyMapperDAO;
import infraestructure.repository.company.records.CompanyRecord;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

public interface CreateCompanyAccountDAO {

    @SqlQuery("INSERT INTO accounts (" +
      "email, " +
      "password, " +
      "status" +
      ") VALUES ( " +
      ":r.email , " +
      ":r.password, " +
      ":r.status ) RETURNING * ")
    @RegisterMapper(AccountMapperDAO.class)
    AccountRecord createAccount(@BindBean("r") AccountRecord record);

    @SqlQuery("INSERT INTO companies (" +
      "name," +
      "admin " +
      ") VALUES ( " +
      ":r.name," +
      ":r.admin ) RETURNING * ")
    @RegisterMapper(CompanyMapperDAO.class)
    CompanyRecord createCompany(@BindBean("r") CompanyRecord record);

}
