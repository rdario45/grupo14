package infraestructure.repository.design;

import controllers.dto.DesignDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.design.records.DesignRecord;

public class DesignMapper {

    public static DesignRecord fromDesigToRecord(Design design) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getDesignStatus().name(),
                design.getOriginalPath(),
                design.getResizedPath(),
                design.getUploadDate(),
                design.getProjectId());
    }


    public static Design fromRecordToDesign(DesignRecord record) {
        return new Design(
          record.getId(),
          record.getEmail(),
          DesignStatus.of(record.getDesignStatus()),
          record.getOriginalPath(),
          record.getResizedPath(),
          record.getUploadDate(),
          record.getProjectId());
    }

    public static DesignDTO fromDesignToDTO(Design design) {
        return new DesignDTO(
            design.getId(),
            design.getEmail(),
            design.getDesignStatus().name(),
            design.getOriginalPath(),
            design.getResizedPath(),
            design.getProjectId(),
            design.getUploadDate()
        );
    }
}
