package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.dto.CreateAccountDTO;
import infraestructure.acl.account.AccountMapper;
import infraestructure.acl.account.AccountValidator;
import infraestructure.repository.account.AccountRepository;
import infraestructure.services.AccountService;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

public class AccountController {

    private final AccountRepository repository;
    private final AccountService accountService;

    @Inject
    public AccountController(AccountRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public Result existsAccount(String email) {
        Option<Result> option = repository.exists(email)
          .map(AccountMapper::fromRecordToJsonDTO)
          .map(Results::ok);

        return option.isDefined() ? option.get() : getNotFound(email);
    }

    public Result createAccount() {
        JsonNode json = request().body().asJson();

        Either<Result, Result> either = getCreateAccountDTO(json)
          .flatMap(dto -> accountService.createAccount(dto)
            .onFailure(throwable -> Logger.error("Error creando cuenta:" + dto.getEmail(), throwable))
            .toEither(getInternalServerError("Error creando cuenta: " + dto.getEmail()))
            .map(AccountMapper::toJsonDTO)
            .map(Results::ok)
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    private Either<Result, CreateAccountDTO> getCreateAccountDTO(JsonNode json) {
        Logger.info(json.toString());
        return Try.of(() -> Json.fromJson(json, CreateAccountDTO.class))
          .onFailure(throwable -> Logger.error("Error en el JSON.", throwable))
          .toEither(List.of("Invalid json"))
          .flatMap(AccountValidator::validateCreateAccount)
          .mapLeft(this::getBadRequest);
    }

    private Result getBadRequest(List<String> errors) {
        return badRequest(Json.newObject()
          .put("message", "Validation errors!")
          .put("fields", String.join(", ", errors)));
    }

    private Result getNotFound(String message) {
        return notFound(this.getJsonMessage(message));
    }

    private Result getInternalServerError(String message) {
        return internalServerError(this.getJsonMessage(message));
    }

    private ObjectNode getJsonMessage(String message) {
        return Json.newObject().put("message", message);
    }
}
