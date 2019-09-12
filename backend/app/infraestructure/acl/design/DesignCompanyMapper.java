package infraestructure.acl.design;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.DesignDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.design.records.DesignCompanyRecord;
import infraestructure.repository.design.records.DesignRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.libs.Json;

import java.sql.Timestamp;

public class DesignCompanyMapper {

    public static JsonNode toJsonDTO(Design design) {
        return Json.toJson(DesignCompanyMapper.fromDesignToDTO(design));
    }

    public static DesignCompanyRecord fromDesignToRecord(Design design) {
        return new DesignCompanyRecord(
          design.getId(),
          design.getEmail(),
          design.getFirstName(),
          design.getLastName(),
          design.getDesignStatus().name(),
          design.getFileName(),
          design.getOriginalPath(),
          design.getResizedPath(),
          new Timestamp(design.getUploadDate().getMillis()),
          design.getPrice(),
          design.getProjectId(),
                design.getCompany().getId(),
                design.getCompany().getName());
    }

    public static Design fromRecordToDesign(DesignCompanyRecord record) {
        return new Design(
                record.getId(),
                record.getEmail(),
                record.getFirstName(),
                record.getLastName(),
                DesignStatus.of(record.getDesignStatus()),
                record.getFileName(),
                record.getOriginalPath(),
                record.getResizedPath(),
                new DateTime(record.getUploadDate(), DateTimeZone.forID("America/Bogota")),
                record.getPrice(),
                record.getProjectId(),
                record.getCompanyId(),
                record.getCompanyName());
    }


    public static DesignDTO fromDesignToDTO(Design design) {
        return new DesignDTO(
          design.getId(),
          design.getEmail(),
          design.getFirstName(),
          design.getLastName(),
          design.getDesignStatus().name(),
          design.getFileName(),
          design.getOriginalPath(),
          design.getResizedPath(),
          design.getUploadDate().toString(),
          design.getPrice(),
          design.getProjectId()
        );
    }

}
