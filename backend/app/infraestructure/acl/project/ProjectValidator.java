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
          validateName(dto.getName()),
          validateDescription(dto.getDescription()),
          validateCost(dto.getCost()),
          Validation.valid(dto.getCompanyId())
        ).ap(Project::new)
          .toEither()
          .mapLeft(List::ofAll);
    }

    static Validation<String, String> validateName(String name) {
        return StringUtils.isNotEmpty(name) && name.length() <= 100 ?
          Validation.valid(name) :
          Validation.invalid("name");
    }

    static Validation<String, String> validateDescription(String description) {
        return StringUtils.isNotEmpty(description) && description.length() <= 200 ?
          Validation.valid(description) :
          Validation.invalid("description");
    }

    static Validation<String, BigDecimal> validateCost(String cost) {
        Logger.info(cost);
        return Try.of(() -> new BigDecimal(cost))
          .toEither("cost")
          .toValidation();
    }

}
