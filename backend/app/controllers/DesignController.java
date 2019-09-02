package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import infraestructure.acl.design.DesignMapper;
import infraestructure.repository.design.DesignRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
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
import static play.mvc.Results.internalServerError;

public class DesignController {

    private DesignRepository repository;

    @Inject
    public DesignController(DesignRepository repository) {
        this.repository = repository;
    }

    public Result findAllDesigns() {
        return ok(Json.toJson(repository.findAll().map(DesignMapper::fromDesignToDTO)));
    }

    public Result findDesign(int id) {
        Either<Result, Result> either = repository.find(id)
                .toEither(getNotFound("Not Found"))
                .map(DesignMapper::toJsonDTO)
                .map(Results::ok);

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result findDesignsByPoject(int id) {
        Logger.debug(String.valueOf(id));
        return ok();
    }

    public Result createDesign() {
        Http.MultipartFormData<Files.TemporaryFile> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> picture = body.getFile("picture");
        Map<String, String[]> stringMap = body.asFormUrlEncoded();

        Files.TemporaryFile file = picture.getRef();
        String fileName = picture.getFilename();
        String firstName = getFirst(stringMap.get("firstName"));
        String lastName = getFirst(stringMap.get("lastName"));
        String email = getFirst(stringMap.get("email"));
        String price = getFirst(stringMap.get("price"));

        Logger.debug(fileName + ", " + email );
        return ok();
    }

    private String getFirst(String[] nombres) {
        return Option.of(nombres).map(List::of).map(List::peek).getOrNull();
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
