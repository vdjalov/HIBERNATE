package app.ccb.services;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import app.ccb.common.Message;
import app.ccb.domain.dtos.jsonImportDto.ClientImportDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.CardRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;

@Service
public class ClientServiceImpl implements ClientService {

	 private final static String CLIENTS_JSON_FILE_PATH =
			 System.getProperty("user.dir") + "/src/main/resources/files/json/clients.json";
	
	private CardRepository cardRepository;
	private EmployeeRepository employeeRepository;
	private ClientRepository clientRepository;
	private ModelMapper modelMapper;
	private Gson gson;
	private FileUtil fileUtil;
	private ValidationUtil validationUtil;
	 
	
	@Autowired
    public ClientServiceImpl(CardRepository cardRepository ,EmployeeRepository employeeRepository, ClientRepository clientRepository, ModelMapper modelMapper, Gson gson, FileUtil fileUtil,
			ValidationUtil validationUtil) {
		this.cardRepository = cardRepository;
		this.employeeRepository = employeeRepository;
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
		this.gson = gson;
		this.fileUtil = fileUtil;
		this.validationUtil = validationUtil;
	}

	public Boolean clientsAreImported() {
       return this.clientRepository.count() != 0;
       
    }

    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH);
    }

    public String importClients(String clients) {
        ClientImportDto[] clientDtos = this.gson.fromJson(clients, ClientImportDto[].class);
    	StringBuilder sBuilder = new StringBuilder();
        
        	for(ClientImportDto clientDto : clientDtos) {
        		String [] employeeNameSplit = clientDto.getAppointedEmployee().split("[\\s]+");
        		String employeeFirstName = employeeNameSplit[0];
        		String employeeLastName = employeeNameSplit[1];
        		Employee employee = this.employeeRepository.findByFirstNameAndLastName(employeeFirstName, employeeLastName);
        			if(employee != null) {		
        				Client currentClient = this.modelMapper.map(clientDto, Client.class);
        				String fullClientName = clientDto.getFirstName() + " " + clientDto.getLastName();
        				currentClient.setFullName(fullClientName);
        					if(this.validationUtil.isValid(currentClient) && this.clientRepository.findByFullName(fullClientName) == null) {
        						currentClient.setBankAccount(null);
        						this.clientRepository.saveAndFlush(currentClient);
        						employee.getClients().add(currentClient);
        						this.employeeRepository.saveAndFlush(employee);
        						sBuilder.append(String.format(Message.SUCCESS_MESSAGE, 
        								"Client", clientDto.getFirstName(), clientDto.getLastName()));
        					} else {
        						sBuilder.append(Message.ERRROR_MESSAGE);
        					}
        			} else {
        				sBuilder.append(Message.ERRROR_MESSAGE);
        			}	
        	}
        return sBuilder.toString();
    }

    public String exportFamilyGuy() {
    	StringBuilder sBuilder = new StringBuilder();
    	List<Object[]> theMan = this.clientRepository.findAndPrintTheCLientWithMostCards();
    
    	int bankAccountId = 0;
    	for(Object topClient[] : theMan) {
    		sBuilder.append(String.format("Name: %s%nAge: %s%nNumber of cards: %s%n",
    				topClient[0].toString(), topClient[1].toString(), topClient[2].toString()));
    	bankAccountId = Integer.valueOf(topClient[3].toString());
    	}
    	
    	List<Object[]> cards = this.cardRepository.cardsByBankAccountId(bankAccountId);
    	
    	sBuilder.append(" Cards:");
    	for(Object card[] : cards) {
    		sBuilder.append(String.format("  Number: %s Status: %s%n", 
    								card[0].toString(), card[1].toString()));
    	}
    	
        return sBuilder.toString();
    }
}
