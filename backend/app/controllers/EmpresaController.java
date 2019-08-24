package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import controllers.dto.EmpresaDTO;
import domain.Empresa;
import infraestructure.acl.EmpresaMapper;
import infraestructure.acl.EmpresaValidator;
import infraestructure.repository.EmpresaRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.function.Function;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class EmpresaController {

    private EmpresaRepository repository;

    @Inject
    public EmpresaController(EmpresaRepository repository) {
        this.repository = repository;
    }

    public Result findAll() {
        return ok(Json.toJson(repository.findAll().map(EmpresaMapper::fromEmpresaToDTO)));
    }

    public Result find(int index) {
        return repository.find(index)
          .map(EmpresaMapper::toJsonDTO)
          .toEither(getNotFoundMessage(index))
          .fold(Results::notFound, Results::ok);
    }

    public Result save() {
        JsonNode json = request().body().asJson();
        Logger.info("POST /save:" + json.toString());

        return getEmpresas(json)
          .map(empresa -> repository.save(empresa))
          .flatMap(future ->
            future.onFailure(throwable -> Logger.error("Error!", throwable))
              .toEither(internalServerError(getJsonMessage("Error guardando empresa!")))
          ).map(EmpresaMapper::toJsonDTO).fold(
            errorHttp -> errorHttp,
            jsonDTO -> ok(jsonDTO)
          );
    }

    public Result update() {
        JsonNode json = request().body().asJson();
        Logger.info("PATCH /update:" + json.toString());

        return getEmpresas(json)
          .map(empresa -> repository.update(empresa))
          .flatMap(future ->
            future.map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
              .onFailure(throwable -> Logger.error("Error!", throwable))
              .toEither(internalServerError(getJsonMessage("Error actualizando Empresa!")))
              .flatMap(Function.identity()))
          .map(EmpresaMapper::toJsonDTO).fold(
            errorHttp -> errorHttp,
            jsonDTO -> ok(jsonDTO)
          );
    }


    public Result delete(int id) {
        return repository.delete(id)
          .map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
          .onFailure(throwable -> Logger.error("Error!", throwable))
          .toEither(internalServerError(getJsonMessage("Error eliminando empresa!")))
          .flatMap(Function.identity())
          .map(EmpresaMapper::toJsonDTO).fold(
            errorHttp -> errorHttp,
            jsonDTO -> ok(jsonDTO)
          );
    }

    private Either<Result, Empresa> getEmpresas(JsonNode json) {
        return getEmpresaDTO(json)
          .flatMap(EmpresaValidator::validate)
          .mapLeft(this::getValidationErrorMessage)
          .mapLeft(Results::badRequest);
    }

    private Either<List<String>, EmpresaDTO> getEmpresaDTO(JsonNode json) {
        Logger.info(json.toString());
        return Try.of(() -> Json.fromJson(json, EmpresaDTO.class))
          .onFailure(throwable -> Logger.error("Error!", throwable))
          .toEither(List.of("Invalid json"));
    }

    private ObjectNode getValidationErrorMessage(List<String> errors) {
        return Json.newObject()
          .put("message", "Validation errors!")
          .put("fields", String.join(", ", errors));
    }

    private ObjectNode getNotFoundMessage(int index) {
        return Json.newObject().put("message", "id " + index + " not found");
    }

    private ObjectNode getJsonMessage(String message) {
        return Json.newObject().put("message", message);
    }

}
