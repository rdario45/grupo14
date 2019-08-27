package infraestructure.acl.project;

import controllers.dto.ProjectDTO;
import domain.Project;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.math.BigDecimal;

public class ProjectValidator {

    public static Either<List<String>, Project> validate(ProjectDTO dto) {
        return Validation.combine(
          Validation.valid(dto.getId()),
          validateNombre(dto.getName()),
          validateDescripcion(dto.getDescription()),
          validateValorEstimado(dto.getCost())
        ).ap(Project::new)
          .toEither()
          .mapLeft(List::ofAll);
    }

    static Validation<String, String> validateNombre(String nombre) {
        return StringUtils.isNotEmpty(nombre) && nombre.length() <= 100 ?
          Validation.valid(nombre) :
          Validation.invalid("nombre");
    }

    static Validation<String, String> validateDescripcion(String descripcion) {
        return StringUtils.isNotEmpty(descripcion) && descripcion.length() <= 200 ?
          Validation.valid(descripcion) :
          Validation.invalid("descripcion");
    }

    static Validation<String, BigDecimal> validateValorEstimado(String valor) {
        Logger.info(valor);
        return Try.of(() -> new BigDecimal(valor))
          .toEither("valor")
          .toValidation();
    }

}
