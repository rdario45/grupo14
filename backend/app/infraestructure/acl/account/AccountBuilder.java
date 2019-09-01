package infraestructure.acl.account;

import domain.Account;
import domain.Company;
import domain.Status;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class AccountBuilder {

    public static Tuple2<Company, Account> buildEmpresaAccount(String companyName,
                                                               String adminEmail,
                                                               String adminPassword) {
        return Tuple.of(new Company(companyName, adminEmail), new Account(adminEmail, adminPassword, Status.ACTIVE));
    }
}