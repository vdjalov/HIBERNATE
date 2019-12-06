package mostwanted.service;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mostwanted.common.Message;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;


@Service
public class TownServiceImpl implements TownService{

    private final static String TOWNS_JSON_FILE_PATH = "C:\\Users\\OK\\workspace\\MostWanted\\src\\main\\resources\\files\\towns.json";
   
    private TownRepository townRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    
    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtilImpl, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
		this.townRepository = townRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.gson = gson;
		this.modelMapper = modelMapper;
	}

	public Boolean townsAreImported() {
       return this.townRepository.count() > 0;
    }

    public String readTownsJsonFile() throws IOException {
        return this.fileUtilImpl.readFile(TOWNS_JSON_FILE_PATH);
    }

    public String importTowns(String townsFileContent) {
    	TownImportDto[] townsDto = this.gson.fromJson(townsFileContent, TownImportDto[].class);
    	StringBuilder sb = new StringBuilder();
    	
    	for(TownImportDto townDto : townsDto) {
    		Town town = this.modelMapper.map(townDto, Town.class);
    			if(this.validationUtil.isValid(town)) {
    				if(this.townRepository.findByName(townDto.getName()) == null) {
    					this.townRepository.saveAndFlush(town);
        				sb.append(String.format(Message.SUCCESSFUL_IMPORT_MESSAGE, "Town", townDto.getName()))
        				  .append(System.lineSeparator());
    				} else {
    					sb.append(Message.DUPLICATE_DATA_MESSAGE)
    					.append(System.lineSeparator());
    				}
    			} else {
    					sb.append(Message.INCORRECT_DATA_MESSAGE)
    					  .append(System.lineSeparator());
    			}
    	}
    	
        return sb.toString();
    }

    public String exportRacingTowns() {
    	List<Object[]> towns = this.townRepository.racingTowns();
    	StringBuilder sBuilder = new StringBuilder();
    	
    	for(Object town[] : towns) {
    		sBuilder.append(String.format("Name: %s%nRacers: %s%n", town[0].toString(), town[1].toString()))
    				.append(System.lineSeparator());
    	}
    	
        return sBuilder.toString();
    }
}











