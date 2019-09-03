package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.dto.CreateDesignDTO;
import domain.Design;
import infraestructure.acl.design.DesignMapper;
import infraestructure.acl.design.DesignValidator;
import infraestructure.repository.design.DesignRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.libs.Files;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.Map;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class DesignController {

    private DesignRepository repository;

    @Inject
    public DesignController(DesignRepository repository) {
        this.repository = repository;
    }

    public Result findDesign(int id) {
        Either<Result, Result> either = repository.find(id)
                .toEither(getNotFound("Not Found"))
                .map(DesignMapper::toJsonDTO)
                .map(Results::ok);

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result findDesignsByPoject(int projectId) {
        return ok(Json.toJson(repository.findByProject(projectId).map(DesignMapper::fromDesignToDTO)));
    }

    public Result createDesign() {
        Http.MultipartFormData<Files.TemporaryFile> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> picture = body.getFile("picture");
        Map<String, String[]> stringMap = body.asFormUrlEncoded();

        Files.TemporaryFile file = picture.getRef();

        JsonNode json =  Json.newObject()
          .put( "fileName",  picture.getFilename() )
          .put( "filePath",  file.path().toAbsolutePath().toString())
          .put( "email",  getFirst(stringMap.get("email")))
          .put( "firstName",  getFirst(stringMap.get("firstName")))
          .put( "lastName",  getFirst(stringMap.get("lastName")))
          .put( "price",  getFirst(stringMap.get("price")))
          .put( "projectId",  getFirst(stringMap.get("projectId")));

        Either<Result, Design> design = getDesign(json);

        return design.fold(
            error -> error,
            d -> ok( Json.toJson(d))
        );
    }

    private JsonNode safeAppend(ObjectNode object, String name, String value) {
        return StringUtils.isNotEmpty(value) ?  object.put(name, value) : object;
    }

    private String getFirst(String[] nombres) {
        return Option.of(nombres).map(List::of).map(List::peek).getOrNull();
    }

    private Either<Result, Design> getDesign(JsonNode json) {
        return Try.of(() -> Json.fromJson(json, CreateDesignDTO.class))
          .onFailure(throwable -> Logger.error("Error en el JSON.", throwable))
          .toEither(List.of("Invalid json"))
          .flatMap(DesignValidator::validate)
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
