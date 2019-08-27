package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import controllers.dto.ProyectoDTO;
import domain.Proyecto;
import infraestructure.acl.proyecto.ProyectoMapper;
import infraestructure.acl.proyecto.ProyectoValidator;
import infraestructure.repository.proyecto.ProyectoRepository;
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

public class ProyectoController {

    private ProyectoRepository repository;

    @Inject
    public ProyectoController(ProyectoRepository repository) {
        this.repository = repository;
    }

    public Result findAll() {
        return ok(Json.toJson(repository.findAll().map(ProyectoMapper::fromProyectoToDTO)));
    }

    public Result find(int index) {
        return repository.find(index)
          .map(ProyectoMapper::toJsonDTO)
          .toEither(getNotFoundMessage(index))
          .fold(Results::notFound, Results::ok);
    }

    public Result save() {
        JsonNode json = request().body().asJson();
        Logger.info("POST /save body:" + json.toString());

        return getProyecto(json)
          .map(proyecto -> repository.save(proyecto))
          .flatMap(future ->
            future.onFailure(throwable -> Logger.error("Error guardando proyecto!", throwable))
              .toEither(internalServerError(getJsonMessage("Error guardando proyecto!")))
          ).map(ProyectoMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }

    public Result update() {
        JsonNode json = request().body().asJson();
        Logger.info("PATCH /update body:" + json.toString());

        return getProyecto(json)
          .map(proyecto -> repository.update(proyecto))
          .flatMap(future ->
            future.map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
              .onFailure(throwable -> Logger.error("Error actualizando Proyecto!", throwable))
              .toEither(internalServerError(getJsonMessage("Error actualizando Proyecto!")))
              .flatMap(Function.identity())
          ).map(ProyectoMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }


    public Result delete(int id) {
        return repository.delete(id)
          .map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
          .onFailure(throwable -> Logger.error("Error eliminando proyecto!", throwable))
          .toEither(internalServerError(getJsonMessage("Error eliminando proyecto!")))
          .flatMap(Function.identity())
          .map(ProyectoMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }

    private Either<Result, Proyecto> getProyecto(JsonNode json) {
        return getProyectoDTO(json)
          .flatMap(ProyectoValidator::validate)
          .mapLeft(this::getValidationErrorMessage)
          .mapLeft(Results::badRequest);
    }

    private Either<List<String>, ProyectoDTO> getProyectoDTO(JsonNode json) {
        Logger.info(json.toString());
        return Try.of(() -> Json.fromJson(json, ProyectoDTO.class))
          .onFailure(throwable -> Logger.error("Error en el JSON.", throwable))
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
