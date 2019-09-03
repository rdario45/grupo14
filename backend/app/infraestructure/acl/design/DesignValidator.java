package infraestructure.acl.design;

import controllers.dto.CreateDesignDTO;
import domain.Design;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class DesignValidator {

    public static Either<List<String>, Design> validate(CreateDesignDTO dto) {
        return Validation.combine(
          validateFileName(dto.getFileName()),
          Validation.valid(dto.getFilePath()),
          validateEmail(dto.getEmail()),
          validateFirstName(dto.getFirstName()),
          validateLastName(dto.getLastName()),
          validatePrice(dto.getPrice()),
          Validation.valid(dto.getProjectId())
        ).ap(DesignBuilder::build)
          .toEither()
          .mapLeft(List::ofAll);
    }

    static Validation<String, String> validateFileName(String fileName) {
        return StringUtils.isNotEmpty(fileName) && fileName.length() <= 50 ? Validation.valid(fileName) : Validation.invalid("fileName");
    }

    static Validation<String, String> validateEmail(String email) {
        return StringUtils.isNotEmpty(email) && email.length() <= 45 ? Validation.valid(email) : Validation.invalid("email");
    }

    static Validation<String, String> validateFirstName(String fistName) {
        return StringUtils.isNotEmpty(fistName) && fistName.length() <= 100 ? Validation.valid(fistName) : Validation.invalid("fistName");
    }

    static Validation<String, String> validateLastName(String lastName) {
        return StringUtils.isNotEmpty(lastName) && lastName.length() <= 45 ? Validation.valid(lastName) : Validation.invalid("lastName");
    }

    static Validation<String, BigDecimal> validatePrice(String price) {
        return Try.of(() -> new BigDecimal(price)).toEither("price").toValidation();
    }

}
