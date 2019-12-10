package app.ccb.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.ccb.common.Message;
import app.ccb.domain.dtos.xmlImportDTO.ImportBankAccountDto;
import app.ccb.domain.dtos.xmlImportDTO.ImportBankAccountRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	 private final static String BANK_ACCOUNTS_XML_FILE_PATH =
			 System.getProperty("user.dir") + "/src/main/resources/files/xml/bank-accounts.xml";
	
	private BankAccountRepository bankAccountRepository;
	private ClientRepository clientRepository;
	private ModelMapper modelMapper;
	private FileUtil fileUtil;
	private ValidationUtil validationUtil;
	private XmlParser xmlParser;
	
	@Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository,
			ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, XmlParser xmlParser) {
		this.bankAccountRepository = bankAccountRepository;
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
		this.fileUtil = fileUtil;
		this.validationUtil = validationUtil;
		this.xmlParser = xmlParser;
	}

	public Boolean bankAccountsAreImported() {
       return this.bankAccountRepository.count() != 0;
    }

    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNTS_XML_FILE_PATH);
    }

    public String importBankAccounts() throws FileNotFoundException, JAXBException {
        ImportBankAccountRootDto bankAccounts = 
        		this.xmlParser.parseXml(ImportBankAccountRootDto.class, BANK_ACCOUNTS_XML_FILE_PATH);
        
        StringBuilder sBuilder = new StringBuilder();
        
        for(ImportBankAccountDto bankAccountDto : bankAccounts.getAllAccounts()) {
        	Client client = this.clientRepository.findByFullName(bankAccountDto.getClientName());
        	
        		if(client != null) {
        			BankAccount ba = this.modelMapper.map(bankAccountDto, BankAccount.class);
        			ba.setClient(client);
        				if(this.validationUtil.isValid(ba)) {
        					this.bankAccountRepository.saveAndFlush(ba);
        					sBuilder.append(String.format(Message.SUCCESS_MESSAGE,
        							"Bank Account", bankAccountDto.getAccounNumber(), ""));
        				} else {
        					sBuilder.append(Message.ERRROR_MESSAGE);
        				}
        		} else {
        			sBuilder.append(Message.ERRROR_MESSAGE);
        		}
        }
        
        return sBuilder.toString();
    }
    
    
    
    
}

















