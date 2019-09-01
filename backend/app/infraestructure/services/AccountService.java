package infraestructure.services;

import controllers.dto.CreateAccountDTO;
import domain.Account;
import domain.Company;
import infraestructure.acl.account.AccountBuilder;
import infraestructure.repository.account.AccountRepository;
import io.vavr.Tuple2;
import io.vavr.concurrent.Future;

import javax.inject.Inject;

public class AccountService {

    private AccountRepository accountRepository;

    @Inject
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Future<Tuple2<Account, Company>> createAccount(CreateAccountDTO dto) {
        Tuple2<Company, Account> tuple = AccountBuilder.buildEmpresaAccount(
          dto.getName(),
          dto.getEmail(),
          dto.getPassword()
        );
        return Future.of(() -> accountRepository.createCompanyAccount(tuple._1, tuple._2));
    }
}