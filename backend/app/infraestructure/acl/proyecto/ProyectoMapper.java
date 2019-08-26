package infraestructure.acl.proyecto;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.ProyectoDTO;
import domain.Proyecto;
import infraestructure.repository.proyecto.records.ProyectoRecord;
import play.libs.Json;

public class ProyectoMapper {

    public static JsonNode toJsonDTO(Proyecto proyecto) {
        return Json.toJson(ProyectoMapper.fromProyectoToDTO(proyecto));
    }

    public static ProyectoDTO fromProyectoToDTO(Proyecto proyecto) {
        return new ProyectoDTO(
          proyecto.getId(),
          proyecto.getNombre(),
          proyecto.getDescripcion(),
          proyecto.getValorEstimado().toString()
        );
    }

    public static ProyectoRecord fromProyectoToRecord(Proyecto proyecto) {
        return new ProyectoRecord(
          proyecto.getId(),
          proyecto.getNombre(),
          proyecto.getDescripcion(),
          proyecto.getValorEstimado()
        );
    }

    public static Proyecto fromRecordToProyecto(ProyectoRecord record) {
        return new Proyecto(
          record.getId(),
          record.getNombre(),
          record.getDescripcion(),
          record.getValorEstimado()
        );
    }
}
