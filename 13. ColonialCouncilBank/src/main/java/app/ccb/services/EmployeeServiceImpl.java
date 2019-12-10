package app.ccb.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import app.ccb.common.Message;
import app.ccb.domain.dtos.jsonImportDto.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final static String EMPLOYEE_JSON_FILE_PATH =
			 System.getProperty("user.dir")+"/src/main/resources/files/json/employees.json";
	
	private BranchRepository branchRepository;
	private EmployeeRepository employeeRepository;
	private FileUtil fileUtil;
	private ModelMapper modelMapper;
	private ValidationUtil validationUtil;
	private Gson gson;
	
	
	
	@Autowired
    public EmployeeServiceImpl(BranchRepository branchRepository ,EmployeeRepository employeeRepository, FileUtil fileUtil, ModelMapper modelMapper,
			ValidationUtil validationUtil, Gson gson) {
		this.branchRepository = branchRepository;
		this.employeeRepository = employeeRepository;
		this.fileUtil = fileUtil;
		this.modelMapper = modelMapper;
		this.validationUtil = validationUtil;
		this.gson = gson;
	}
    

	public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_JSON_FILE_PATH);
    }

    public String importEmployees(String employees) {
    	EmployeeImportDto[] employeeDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);
    	StringBuilder sBuilder = new StringBuilder();
    	
    		for(EmployeeImportDto employee: employeeDtos) {
    			Optional<Branch> branch = this.branchRepository.findByName(employee.getBranch());
    				if(branch.isPresent()) {
    					Employee currentEmployee = this.modelMapper.map(employee, Employee.class);
    	    			String namesSplit[] = employee.getFullName().split("[ ]+");
    	    			currentEmployee.setFirstName(namesSplit[0]);
    	    			currentEmployee.setLastName(namesSplit[1]);
    	    			currentEmployee.setBranch(branch.get());
    	    				if(this.validationUtil.isValid(employee)) {
    	    					this.employeeRepository.saveAndFlush(currentEmployee);
    	    				} else {
    	    					sBuilder.append(Message.ERRROR_MESSAGE);
    	    				}
    				} else {
    					sBuilder.append(Message.ERRROR_MESSAGE);
    				}
    		}
        return sBuilder.toString();
    }

    public String exportTopEmployees() {
       List<Object[]> topEmployees = this.employeeRepository.employeesByNumberOfClients();
       StringBuilder sBuilder = new StringBuilder();
       
       	for(Object employee[] : topEmployees) {
       		sBuilder.append(String.format("%s %s - %s clients.%n",employee[0], employee[1], employee[2] ));
       	}
       	
        return sBuilder.toString();
    }
    
    
    
    
}













