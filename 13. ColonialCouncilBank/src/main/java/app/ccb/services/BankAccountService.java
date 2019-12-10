package app.ccb.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface BankAccountService {

    Boolean bankAccountsAreImported();

    String readBankAccountsXmlFile() throws IOException;

    String importBankAccounts() throws FileNotFoundException, JAXBException;
}
