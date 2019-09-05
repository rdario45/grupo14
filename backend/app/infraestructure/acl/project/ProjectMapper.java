package infraestructure.acl.project;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.ProjectDTO;
import domain.Project;
import infraestructure.repository.project.records.ProjectRecord;
import play.libs.Json;

public class ProjectMapper {

    public static JsonNode toJsonDTO(Project project) {
        return Json.toJson(ProjectMapper.toDTO(project));
    }

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
          project.getId(),
          project.getName(),
          project.getDescription(),
          project.getCost().toString(),
          project.getCompanyId());
    }

    public static ProjectRecord fromProjectToRecord(Project project, int id) {
        return new ProjectRecord(
          id,
          project.getName(),
          project.getDescription(),
          project.getCost(),
          project.getCompanyId());
    }

    public static ProjectRecord fromProjectToRecord(Project project) {
        return new ProjectRecord(
          project.getId(),
          project.getName(),
          project.getDescription(),
          project.getCost(),
          project.getCompanyId());
    }

    public static Project fromRecordToProject(ProjectRecord record) {
        return new Project(
          record.getId(),
          record.getName(),
          record.getDescription(),
          record.getCost(),
          record.getCompanyId());
    }
}
