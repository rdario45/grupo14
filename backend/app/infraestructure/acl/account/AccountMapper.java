package infraestructure.acl.account;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.AccountDTO;
import domain.Account;
import infraestructure.repository.account.records.AccountRecord;
import play.libs.Json;

public class AccountMapper {

    public static JsonNode toJsonDTO(Account account) {
        return Json.toJson(fromAccountToDTO(account));
    }

    public static AccountDTO fromAccountToDTO(Account account) {
        return new AccountDTO(
          account.getEmail(),
          account.getStatus()
        );
    }

    public static JsonNode fromRecordToJsonDTO(AccountRecord record) {
        return Json.toJson(
          new AccountDTO(
            record.getEmail(),
            record.getStatus()
          )
        );
    }

}
