package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.dto.LoginDTO;
import infraestructure.acl.account.AccountMapper;
import infraestructure.acl.account.AccountValidator;
import infraestructure.services.AccountService;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

public class AuthController {

    private final AccountService accountService;

    @Inject
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    public Result login() {
        JsonNode json = request().body().asJson();

        Either<Result, Result> either = getLoginDTO(json)
          .flatMap(dto -> accountService.login(dto)
            .onFailure(throwable -> Logger.error("Error login", throwable))
            .toEither(getInternalServerError("Error login " + dto.getEmail()))
            .flatMap(option -> option.toEither(getNotFound("Not Found")))
            .map(AccountMapper::toJsonDTO)
            .map(Results::ok)
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result logout() {
        return ok();
    }

    private Either<Result, LoginDTO> getLoginDTO(JsonNode json) {
        Logger.info(json.toString());
        return Try.of(() -> Json.fromJson(json, LoginDTO.class))
          .toEither(List.of("Invalid json"))
          .flatMap(AccountValidator::validateLogin)
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
