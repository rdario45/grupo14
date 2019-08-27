package infraestructure.acl.project;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.ProjectDTO;
import domain.Project;
import infraestructure.repository.project.records.ProjectRecord;
import play.libs.Json;

public class ProjectMapper {

    public static JsonNode toJsonDTO(Project project) {
        return Json.toJson(ProjectMapper.fromProyectoToDTO(project));
    }

    public static ProjectDTO fromProyectoToDTO(Project project) {
        return new ProjectDTO(
          project.getId(),
          project.getName(),
          project.getDescription(),
          project.getPrice().toString()
        );
    }

    public static ProjectRecord fromProjectToRecord(Project project) {
        return new ProjectRecord(
          project.getId(),
          project.getName(),
          project.getDescription(),
          project.getPrice()
        );
    }

    public static Project fromRecordToProject(ProjectRecord record) {
        return new Project(
          record.getId(),
          record.getNombre(),
          record.getDescripcion(),
          record.getValorEstimado()
        );
    }
}
