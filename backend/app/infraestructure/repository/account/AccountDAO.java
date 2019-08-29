package infraestructure.repository.account;

import infraestructure.repository.account.records.AccountRecord;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(AccountMapperDAO.class)
public interface AccountDAO {

    @SqlQuery("SELECT email, status FROM accounts WHERE email = :email")
    AccountRecord find(@Bind("email") String email);

}
