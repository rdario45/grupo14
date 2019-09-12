package infraestructure.repository.design.records;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DesignCompanyRecord extends DesignRecord{

    private int companyId;
    private String companyName;


    public DesignCompanyRecord(int id, String email, String firstName, String lastName, String designStatus, String folder, String fileName,
                               String originalPath, String resizedPath, Timestamp uploadDate, BigDecimal price, int projectId,
                               int companyId, String companyName) {
        super(id, email, firstName, lastName, designStatus, folder, fileName, originalPath, resizedPath, uploadDate, price, projectId);
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
