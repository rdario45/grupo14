package infraestructure.repository.company;

import controllers.dto.CompanyDTO;
import domain.Company;
import infraestructure.repository.company.records.CompanyRecord;

public class CompanyMapper {

    public static CompanyRecord fromCompanyToRecord(Company company) {
        return new CompanyRecord(
          company.getId(),
          company.getName(),
          company.getAdmin()
        );
    }

    public static Company fromRecordToCompany(CompanyRecord record) {
        return new Company(
          record.getId(),
          record.getName(),
          record.getAdmin());
    }

    public static CompanyDTO fromCompanyToDTO(Company company) {
        return new CompanyDTO(
          company.getId(),
          company.getName(),
          company.getAdmin()
        );
    }
}