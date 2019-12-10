package app.ccb.services;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import app.ccb.common.Message;
import app.ccb.domain.dtos.jsonImportDto.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;

@Service
public class BranchServiceImpl implements BranchService {

	 private final static String BRANCH_JSON_FILE_PATH =
			 System.getProperty("user.dir") + "/src/main/resources/files/json/branches.json";
	
	private BranchRepository branchRepository;
	private FileUtil fileUtil;
	private ModelMapper modelMapper;
	private ValidationUtil validationUtil;
	private Gson gson;
	
	
	@Autowired
	public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, ModelMapper modelMapper,
			ValidationUtil validationUtil, Gson gson) {
		this.branchRepository = branchRepository;
		this.fileUtil = fileUtil;
		this.modelMapper = modelMapper;
		this.validationUtil = validationUtil;
		this.gson = gson;
	}

	public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCH_JSON_FILE_PATH);
    }

    public String importBranches(String branchesJson) {
       BranchImportDto [] branches = this.gson.fromJson(branchesJson, BranchImportDto[].class);
       StringBuilder sBuilder = new StringBuilder();
       
       	for(BranchImportDto currentBranch : branches) {
       		Branch branch = this.modelMapper.map(currentBranch, Branch.class);
       		
       			if(this.validationUtil.isValid(branch)) {
       				this.branchRepository.saveAndFlush(branch);
       				sBuilder.append(String.format(Message.SUCCESS_MESSAGE, "Branch", currentBranch.getName(), "Branch"));
       			} else {
       				sBuilder.append(Message.ERRROR_MESSAGE);
       			}
       	}
        return sBuilder.toString();
    }
    
    
    
}









