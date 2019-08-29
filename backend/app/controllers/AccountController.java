package controllers;

import infraestructure.repository.account.AccountRepository;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

public class AccountController {

    AccountRepository repository;

    @Inject
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    public Result createAccount() {
        return ok();
    }

    public Result existsAccount(String email) {
        return repository.exists(email).isDefined() ? ok() : notFound();
    }
}
