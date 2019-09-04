package infraestructure.acl.account;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.AccountCreatedDTO;
import controllers.dto.AccountDTO;
import controllers.dto.LoginDTO;
import domain.Account;
import domain.Company;
import domain.Status;
import infraestructure.repository.account.records.AccountCompanyRecord;
import infraestructure.repository.account.records.AccountRecord;
import infraestructure.repository.company.CompanyMapper;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import play.libs.Json;

public class AccountMapper {

    public static JsonNode toJsonDTO(Tuple2<Account, Company> tuple) {
        return Json.toJson(
          new AccountCreatedDTO(
            CompanyMapper.fromCompanyToDTO(tuple._2),
            fromAccountToDTO(tuple._1)
          )
        );
    }

    public static AccountDTO fromAccountToDTO(Account account) {
        return new AccountDTO(
          account.getEmail(),
          account.getStatus().name()
        );
    }

    public static JsonNode fromRecordToJsonDTO(AccountRecord record) {
        return Json.toJson(new AccountDTO(
          record.getEmail(),
          record.getStatus()
        ));
    }

    public static AccountRecord fromAccountToRecord(Account account) {
        return new AccountRecord(
          account.getEmail(),
          account.getPassword(),
          account.getStatus().name()
        );
    }

    public static Account fromRecordToAccount(AccountRecord record) {
        return new Account(
          record.getEmail(),
          null,
          Status.of(record.getStatus())
        );
    }

    public static Tuple2<Account, Company> fromAccountCompanyRecordToTuple(AccountCompanyRecord record) {
        return Tuple.of(
          new Account(
            record.getAdminEmail(),
            null,
            Status.of(record.getAdminStatus())
          ),
          new Company(
            record.getCompanyId(),
            record.getCompanyName(),
            record.getAdminEmail()
          )
        );
    }

}
