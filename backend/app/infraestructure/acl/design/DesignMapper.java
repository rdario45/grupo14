package infraestructure.acl.design;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.DesignDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.design.records.DesignRecord;
import play.libs.Json;

public class DesignMapper {

    public static JsonNode toJsonDTO(Design design) {
        return Json.toJson(DesignMapper.fromDesignToDTO(design));
    }

    public static DesignDTO fromDesignToDTO(Design design) {
        return new controllers.dto.DesignDTO(
                design.getId(),
                design.getEmail(),
                design.getDesignStatus().name(),
                design.getOriginalPath(),
                design.getStretchedPath());
    }

    public static DesignRecord fromDesignToRecord(Design design, int id) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getDesignStatus().name(),
                design.getOriginalPath(),
                design.getStretchedPath());
    }

    public static DesignRecord fromDesignToRecord(Design design) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getDesignStatus().name(),
                design.getOriginalPath(),
                design.getStretchedPath());
    }

    public static Design fromRecordToDesign(DesignRecord record) {
        return new Design(
                record.getId(),
                record.getEmail(),
                DesignStatus.of(record.getDesignStatus()),
                record.getOriginalPath(),
                record.getResizedPath());
    }
}
