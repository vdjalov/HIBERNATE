package app.ccb.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.ccb.common.Message;
import app.ccb.domain.dtos.xmlImportDTO.ImportCardDto;
import app.ccb.domain.dtos.xmlImportDTO.ImportCardRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;

@Service
public class CardServiceImpl implements CardService {

	 private final static String CARDS_XML_FILE_PATH =
			 System.getProperty("user.dir") + "/src/main/resources/files/xml/cards.xml";
	
	private CardRepository cardRepository;
	private BankAccountRepository bankAccountRepository;
	private ModelMapper modelMapper;
	private FileUtil fileUtil;
	private ValidationUtil validationUtil;
	private XmlParser xmlParser;
	
	
	@Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository,
			ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil, XmlParser xmlParser) {
		this.cardRepository = cardRepository;
		this.bankAccountRepository = bankAccountRepository;
		this.modelMapper = modelMapper;
		this.fileUtil = fileUtil;
		this.validationUtil = validationUtil;
		this.xmlParser = xmlParser;
	}

	
	public Boolean cardsAreImported() {
       return this.cardRepository.count() != 0; 
    }

	
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile(CARDS_XML_FILE_PATH);
    }

    
    public String importCards() throws FileNotFoundException, JAXBException {
    	ImportCardRootDto importCardRootDto = this.xmlParser.parseXml(ImportCardRootDto.class, CARDS_XML_FILE_PATH);  	
    	StringBuilder sBuilder = new StringBuilder();
    	
    	for(ImportCardDto importCardDto: importCardRootDto.getAllCards()) {
    		BankAccount bankAccount = this.bankAccountRepository.findByAccountNumber(importCardDto.getAccountNumber());
    		
    			if(bankAccount != null) {
    				Card card = this.modelMapper.map(importCardDto, Card.class);
    				card.setBankAccount(bankAccount);
    					if(this.validationUtil.isValid(card)) {
    						this.cardRepository.saveAndFlush(card);
    						sBuilder.append(String.format(Message.SUCCESS_MESSAGE,
    								"Card", importCardDto.getCardNumber(), ""));
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


















