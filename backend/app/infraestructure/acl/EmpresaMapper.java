package infraestructure.acl;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.EmpresaDTO;
import domain.Empresa;
import infraestructure.repository.records.EmpresaRecord;
import play.libs.Json;

public class EmpresaMapper {

    public static JsonNode toJsonDTO(Empresa empresa) {
        return Json.toJson(EmpresaMapper.fromEmpresaToDTO(empresa));
    }

    public static EmpresaDTO fromEmpresaToDTO(Empresa empresa) {
        return new EmpresaDTO(
          empresa.getId(),
          empresa.getNombre()
        );
    }

    public static EmpresaRecord fromEmpresaToRecord(Empresa empresa) {
        return new EmpresaRecord(
          empresa.getId(),
          empresa.getNombre()
        );
    }

    public static Empresa fromRecordToEmpresa(EmpresaRecord record) {
        return new Empresa(
          record.getId(),
          record.getNombre()
        );
    }
}
