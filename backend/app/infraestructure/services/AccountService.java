package infraestructure.services;

import controllers.dto.CreateAccountDTO;
import domain.Account;
import io.vavr.concurrent.Future;

public class AccountService {

    public Future<Account> createAccount(CreateAccountDTO dto) {
        // TODO: finish
        return Future.successful(new Account( dto.getEmail(), dto.getPassword(), "ACTIVE" ));
    }
}
