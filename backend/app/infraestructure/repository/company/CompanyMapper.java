package infraestructure.repository.company;

import controllers.dto.CompanyDTO;
import domain.Company;
import infraestructure.repository.company.records.CompanyRecord;

public class CompanyMapper {

    public static CompanyRecord fromCompanyToRecord(Company company) {
        return new CompanyRecord(
          company.getId(),
          company.getName(),
          company.getAdmin(),
          company.getUrl()
        );
    }

    public static Company fromRecordToCompany(CompanyRecord company) {
        return new Company(
                company.getId(),
                company.getName(),
                company.getAdmin(),
                company.getUrl()
        );
    }

    public static CompanyDTO fromCompanyToDTO(Company company) {
        return new CompanyDTO(
          company.getId(),
          company.getName(),
          company.getAdmin(),
          company.getUrl()
        );
    }
}
