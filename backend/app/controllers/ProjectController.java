package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import controllers.dto.ProjectDTO;
import domain.Project;
import infraestructure.acl.project.ProjectMapper;
import infraestructure.acl.project.ProjectValidator;
import infraestructure.repository.project.ProjectRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class ProjectController {

    private ProjectRepository repository;

    @Inject
    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    public Result findAllProjects() {
        return ok(Json.toJson(repository.findAll().map(ProjectMapper::fromAccountToDTO)));
    }

    public Result findProject(int id) {
        Either<Result, Result> either = repository.find(id)
          .toEither(getNotFound("Not Found"))
          .map(ProjectMapper::toJsonDTO)
          .map(Results::ok);

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result createProject() {
        JsonNode json = request().body().asJson();

        Either<Result, Result> either = getProject(json)
          .map(project -> repository.save(project))
          .flatMap(future ->
            future.onFailure(throwable -> Logger.error("Error guardando proyecto!", throwable))
              .toEither(getInternalServerError("Error guardando proyecto!"))
              .map(ProjectMapper::toJsonDTO)
              .map(Results::ok)
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result updateProject(int id) {
        JsonNode json = request().body().asJson();

        Either<Result, Result> either = getProject(json)
          .map(project -> repository.update(project, id))
          .flatMap(future ->
            future.onFailure(throwable -> Logger.error("Error actualizando Proyecto!", throwable))
              .toEither(getInternalServerError("Error actualizando Proyecto!"))
              .flatMap(option -> option.toEither(getNotFound("Not Found")))
              .map(ProjectMapper::toJsonDTO)
              .map(Results::ok)
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result deleteProject(int id) {
        Either<Result, Result> either = repository.delete(id)
          .onFailure(throwable -> Logger.error("Error eliminando proyecto!", throwable))
          .toEither(getInternalServerError("Error eliminando proyecto!"))
          .flatMap(option -> option.toEither(getNotFound("Not Found")))
          .map(ProjectMapper::toJsonDTO)
          .map(Results::ok);

        return either.isRight() ? either.get() : either.getLeft();
    }

    private Either<Result, Project> getProject(JsonNode json) {
        return Try.of(() -> Json.fromJson(json, ProjectDTO.class))
          .onFailure(throwable -> Logger.error("Error en el JSON.", throwable))
          .toEither(List.of("Invalid json"))
          .flatMap(ProjectValidator::validate)
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
