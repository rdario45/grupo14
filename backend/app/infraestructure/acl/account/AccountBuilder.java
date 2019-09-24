package infraestructure.acl.account;

import controllers.dto.LoginDTO;
import domain.Account;
import domain.Company;
import domain.Status;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class AccountBuilder {

    public static Tuple2<Company, Account> buildEmpresaAccount(String companyName,
                                                               String adminEmail,
                                                               String adminPassword,
                                                               String url) {
        String shortCompanyName = companyName.toLowerCase().replaceAll("[^a-zA-Z0-9]+","");
        return Tuple.of(new Company(companyName, adminEmail, url.replace("{0}",shortCompanyName)),
                new Account(adminEmail, adminPassword, Status.ACTIVE)); 
    }

    public static Account build(LoginDTO dto) {
        return new Account(dto.getEmail(), dto.getPassword(), Status.ACTIVE);
    }
}
