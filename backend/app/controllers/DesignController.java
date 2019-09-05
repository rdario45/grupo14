package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import controllers.dto.CreateDesignDTO;
import controllers.dto.DesignsPaginatedDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.acl.design.DesignMapper;
import infraestructure.acl.design.DesignValidator;
import infraestructure.repository.design.DesignRepository;
import infraestructure.services.DesignService;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Files;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class DesignController {

    private DesignRepository repository;
    private DesignService designService;
    private final Config config;


    @Inject
    public DesignController(DesignRepository repository, DesignService designService, Config config) {
        this.repository = repository;
        this.designService = designService;
        this.config = config;
    }

    public Result download(int projectId) {
        Either<Result, Result> either = repository.find(projectId)
          .toEither(getNotFound("Not Found"))
          .map(design -> design.getOriginalPath() != null ?
            ok(new File(design.getOriginalPath())) :
            notFound()
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result downloadResized(int projectId) {
        Either<Result, Result> either = repository.find(projectId)
          .toEither(getNotFound("Not Found"))
          .map(design -> design.getResizedPath() != null ?
            ok(new File(design.getResizedPath())) :
            badRequest()
          );

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result findDesign(int id) {
        Either<Result, Result> either = repository.find(id)
          .toEither(getNotFound("Not Found"))
          .map(DesignMapper::toJsonDTO)
          .map(Results::ok);

        return either.isRight() ? either.get() : either.getLeft();
    }

    public Result findDesignsByPojectPaginated(int projectId, int page) {
        int pageSize = config.getInt("pagination.size");
        int offset = (page - 1) * pageSize;
        DesignsPaginatedDTO result = repository.findByProjectPaginated(projectId, offset, pageSize)
          .map1(list -> list.map(DesignMapper::fromDesignToDTO))
          .apply(DesignsPaginatedDTO::new);
        return ok(Json.toJson(result));
    }

    public Result findDesignsByPojectAndStatusPaginated(int projectId, String status, int page) {
        int pageSize = config.getInt("pagination.size");
        int offset = (page - 1) * pageSize;
        DesignsPaginatedDTO result = repository.findByProjectAndStatus(projectId, DesignStatus.of(status), offset, pageSize)
          .map1(list -> list.map(DesignMapper::fromDesignToDTO))
          .apply(DesignsPaginatedDTO::new);
        return ok(Json.toJson(result));
    }

    public Result createDesign() {
        Http.MultipartFormData<Files.TemporaryFile> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> picture = body.getFile("picture");
        Map<String, String[]> stringMap = body.asFormUrlEncoded();
        Files.TemporaryFile file = picture.getRef();

        String workdir = config.getString("files.workdir");
        File originalPath = new File(workdir + UUID.randomUUID().toString());
        originalPath.mkdir();
        String originalFullPath = originalPath.getAbsolutePath() + "/" + picture.getFilename();
        Path path = file.copyTo(Paths.get(originalFullPath), true);
        Logger.debug("*** new file: " + originalFullPath);

        JsonNode json = Json.newObject()
          .put("fileName", picture.getFilename())
          .put("filePath", path.toString())
          .put("email", getFirst(stringMap.get("email")))
          .put("firstName", getFirst(stringMap.get("firstName")))
          .put("lastName", getFirst(stringMap.get("lastName")))
          .put("price", getFirst(stringMap.get("price")))
          .put("projectId", getFirst(stringMap.get("projectId")));

        Either<Result, Result> either = getDesign(json)
          .map(d -> designService.createDesign(d))
          .flatMap(future -> future
            .onFailure(throwable -> Logger.error("Error creando el diseño.", throwable))
            .toEither(getInternalServerError("Error guardando proyecto!"))
            .map(DesignMapper::toJsonDTO)
            .map(Results::ok)
          );
        return either.isRight() ? either.get() : either.getLeft();

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
