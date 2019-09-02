package infraestructure.repository.design;

import controllers.dto.DesignDTO;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.design.records.DesignRecord;

public class DesignMapper {

    public static DesignRecord fromCompanyToRecord(Design design) {
        return new DesignRecord(
                design.getId(),
                design.getEmail(),
                design.getDesignStatus().name(),
                design.getOriginalPath(),
                design.getResizedPath(),
                design.getProjectId());
    }


    public static Design fromRecordToCompany(DesignRecord record) {
        return new Design(
          record.getId(),
          record.getEmail(),
          DesignStatus.of(record.getDesignStatus()),
          record.getOriginalPath(),
          record.getResizedPath(),
          record.getProjectId());
    }

    public static DesignDTO fromCompanyToDTO(Design design) {
        return new DesignDTO(
            design.getId(),
            design.getEmail(),
            design.getDesignStatus().name(),
            design.getOriginalPath(),
            design.getResizedPath(),
            design.getProjectId()
        );
    }
}
