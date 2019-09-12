package infraestructure.acl.design;

import domain.Design;
import domain.DesignStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.math.BigDecimal;

public class DesignBuilder {

    public static Design  build(String folder,
                                String fileName,
                                String filePath,
                                String email,
                                String firstName,
                                String lastName,
                                BigDecimal price,
                                int projectId) {

        return new Design(email, firstName, lastName, DesignStatus.PROCESSING, folder, fileName, filePath, DateTime.now(DateTimeZone.forID("America/Bogota")), price, projectId);
    }
}
