package infraestructure.acl.account;

import controllers.dto.CreateAccountDTO;
import io.vavr.collection.List;
import io.vavr.control.Either;

public class AccountValidator {

    public static Either<List<String>, CreateAccountDTO> validateCreateAccount(CreateAccountDTO dto) {

        // TODO: finish
        return Either.right(dto);
    }
}
