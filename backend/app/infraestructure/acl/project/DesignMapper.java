package infraestructure.acl.project;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.DesignDTO;
import domain.Design;
import infraestructure.repository.design.records.DesignRecord;
import play.libs.Json;

public class DesignMapper {

    public static JsonNode toJsonDTO(Design design) {
        return Json.toJson(DesignMapper.fromDesignToDTO(design));
    }

    public static DesignDTO fromDesignToDTO(Design project) {
        return new controllers.dto.DesignDTO(
                project.getId(),
                project.getEmail(),
                project.getStretched(),
                project.getOriginalPath(),
                project.getStretchedPath());
    }

    public static DesignRecord fromDesignToRecord(Design design, int id) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getStretched(),
                design.getOriginalPath(),
                design.getStretchedPath());
    }

    public static DesignRecord fromDesignToRecord(Design design) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getStretched(),
                design.getOriginalPath(),
                design.getStretchedPath());
    }

    public static Design fromRecordToDesign(DesignRecord record) {
        return new Design(
                record.getId(),
                record.getEmail(),
                record.getStretched(),
                record.getOriginalPath(),
                record.getStretchedPath());
    }
}
