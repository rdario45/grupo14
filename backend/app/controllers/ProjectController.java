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

import java.util.function.Function;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class ProjectController {

    private ProjectRepository repository;

    @Inject
    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    public Result findAllProjects() {
        return ok(Json.toJson(repository.findAll().map(ProjectMapper::fromProjectToDTO)));
    }

    public Result findProject(int id) {
        return repository.find(id)
          .map(ProjectMapper::toJsonDTO)
          .toEither(getNotFoundMessage(id))
          .fold(Results::notFound, Results::ok);
    }

    public Result createProject() {
        JsonNode json = request().body().asJson();
        Logger.info("/create body:" + json.toString());

        return getProject(json)
          .map(project -> repository.save(project))
          .flatMap(future ->
            future.onFailure(throwable -> Logger.error("Error guardando proyecto!", throwable))
              .toEither(internalServerError(getJsonMessage("Error guardando proyecto!")))
          ).map(ProjectMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }

    public Result updateProject(int id) {
        JsonNode json = request().body().asJson();
        Logger.info("/update body:" + json.toString());

        return getProject(json)
          .map(project -> repository.update(project, id))
          .flatMap(future ->
            future.map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
              .onFailure(throwable -> Logger.error("Error actualizando Proyecto!", throwable))
              .toEither(internalServerError(getJsonMessage("Error actualizando Proyecto!")))
              .flatMap(Function.identity())
          ).map(ProjectMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }

    public Result deleteProject(int id) {
        return repository.delete(id)
          .map(option -> option.toEither(notFound(getJsonMessage("Not Found"))))
          .onFailure(throwable -> Logger.error("Error eliminando proyecto!", throwable))
          .toEither(internalServerError(getJsonMessage("Error eliminando proyecto!")))
          .flatMap(Function.identity())
          .map(ProjectMapper::toJsonDTO)
          .fold(errorHttp -> errorHttp, Results::ok);
    }

    private Either<List<String>, ProjectDTO> getProjectDTO(JsonNode json) {
        Logger.info(json.toString());
        return Try.of(() -> Json.fromJson(json, ProjectDTO.class))
          .onFailure(throwable -> Logger.error("Error en el JSON.", throwable))
          .toEither(List.of("Invalid json"));
    }

    private Either<Result, Project> getProject(JsonNode json) {
        return getProjectDTO(json)
          .flatMap(ProjectValidator::validate)
          .mapLeft(this::getValidationErrorMessage)
          .mapLeft(Results::badRequest);
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
