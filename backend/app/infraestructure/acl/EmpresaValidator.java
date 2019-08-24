package infraestructure.acl;

import controllers.dto.EmpresaDTO;
import domain.Empresa;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

public class EmpresaValidator {

    public static Either<List<String>, Empresa> validate(EmpresaDTO dto) {
        return Validation.combine(
          Validation.valid(dto.getId()),
          validateNombre(dto.getNombre())
        ).ap(Empresa::new)
          .toEither()
          .mapLeft(List::ofAll);
    }

    static Validation<String, String> validateNombre(String nombre) {
        return StringUtils.isNotEmpty(nombre) && nombre.length() <= 100 ?
          Validation.valid(nombre) :
          Validation.invalid("nombre");
    }

}
