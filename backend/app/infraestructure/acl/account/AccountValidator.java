package infraestructure.acl.account;

import controllers.dto.CreateAccountDTO;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

public class AccountValidator {

    public static Either<List<String>, CreateAccountDTO> validateCreateAccount(CreateAccountDTO dto) {

        return Validation.combine(
          validateName(dto.getName()),
          validateEmail(dto.getEmail()),
          validatePassword(dto.getPassword())
        ).ap(CreateAccountDTO::new)
          .toEither()
          .mapLeft(List::ofAll);
    }

    static Validation<String, String> validateName(String name) {
        return StringUtils.isNotEmpty(name) && name.length() <= 100 ?
          Validation.valid(name) :
          Validation.invalid("name");
    }

    private static Validation<String, String> validateEmail(String email) {
        return StringUtils.isNotEmpty(email) && email.length() <= 100 ?
          Validation.valid(email) :
          Validation.invalid("email");
    }

    private static Validation<String, String> validatePassword(String password) {
        return StringUtils.isNotEmpty(password) && password.length() >= 4 && password.length() <= 50 ?
          Validation.valid(password) :
          Validation.invalid("password");
    }
}