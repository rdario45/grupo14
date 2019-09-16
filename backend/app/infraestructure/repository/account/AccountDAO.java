package infraestructure.repository.account;

import infraestructure.repository.account.records.AccountCompanyRecord;
import infraestructure.repository.account.records.AccountRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(AccountMapperDAO.class)
public interface AccountDAO {

    @SqlQuery("SELECT email, status FROM accounts WHERE email = :email")
    AccountRecord find(@Bind("email") String email);

    @SqlQuery("SELECT " +
      "c.id as companyId, " +
      "c.name as companyName, " +
      "a.email as adminEmail, " +
      "a.status as adminStatus " +
      "FROM accounts a " +
      "left JOIN companies c on (c.admin = a.email) " +
      "WHERE a.email = :a.email " +
      "and a.password = :a.password ")
    @RegisterMapper(AccountCompanyMapperDAO.class)
    AccountCompanyRecord login(@BindBean("a") AccountRecord account);
}
