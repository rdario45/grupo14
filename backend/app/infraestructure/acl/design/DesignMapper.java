package infraestructure.acl.design;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.DesignDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.design.records.DesignRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.libs.Json;

import java.sql.Timestamp;

public class DesignMapper {

    public static JsonNode toJsonDTO(Design design) {
        return Json.toJson(DesignMapper.fromDesignToDTO(design));
    }

    public static DesignRecord fromDesignToRecord(Design design) {
        return new DesignRecord(
          design.getId(),
          design.getEmail(),
          design.getFirstName(),
          design.getLastName(),
          design.getDesignStatus().name(),
          design.getOriginalPath(),
          design.getResizedPath(),
          new Timestamp(design.getUploadDate().getMillis()),
          design.getPrice(),
          design.getProjectId());
    }


    public static Design fromRecordToDesign(DesignRecord record) {
        return new Design(
          record.getId(),
          record.getEmail(),
          record.getFirstName(),
          record.getLastName(),
          DesignStatus.of(record.getDesignStatus()),
          record.getOriginalPath(),
          record.getResizedPath(),
          new DateTime(record.getUploadDate(), DateTimeZone.forID("America/Bogota")),
          record.getPrice(),
          record.getProjectId());
    }

    public static DesignDTO fromDesignToDTO(Design design) {
        return new DesignDTO(
          design.getId(),
          design.getEmail(),
          design.getFirstName(),
          design.getLastName(),
          design.getDesignStatus().name(),
          design.getOriginalPath(),
          design.getResizedPath(),
          design.getUploadDate().toString(),
          design.getPrice(),
          design.getProjectId()
        );
    }

}
