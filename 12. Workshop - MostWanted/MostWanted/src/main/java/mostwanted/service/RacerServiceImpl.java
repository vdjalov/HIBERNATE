package mostwanted.service;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mostwanted.common.Message;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private RacerRepository racerRepository;
    private TownRepository townRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    
    
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtilImpl,
			ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
		this.racerRepository = racerRepository;
		this.townRepository = townRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.gson = gson;
		this.modelMapper = modelMapper;
	}

	public Boolean racersAreImported() {
        return racerRepository.count() > 0;
    }

    public String readRacersJsonFile() throws IOException {
        return this.fileUtilImpl.readFile(RACERS_JSON_FILE_PATH);
    }

    public String importRacers(String racersFileContent) {
    	RacerImportDto [] racers = this.gson.fromJson(racersFileContent, RacerImportDto[].class); 
    	StringBuilder sBuilder = new StringBuilder();
    	
    		for(RacerImportDto racerImportDto : racers) {
    			Town town = this.townRepository.findByName(racerImportDto.getHomeTown());
    			
    				if(town == null) {
    					sBuilder.append(Message.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
    				} else {
    					Racer racer = this.racerRepository.findByName(racerImportDto.getName());
    					if(racer == null) {
    						racer = this.modelMapper.map(racerImportDto, Racer.class);
    						racer.setHomeTown(town);
    						if(this.validationUtil.isValid(racer)) {
    							this.racerRepository.saveAndFlush(racer);
        						sBuilder.append(String.format(Message.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racerImportDto.getName()))
        								.append(System.lineSeparator());
    						} else {
    							sBuilder.append(Message.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
    						}
    						
    					} else {
    						sBuilder.append(Message.DUPLICATE_DATA_MESSAGE)
    								.append(System.lineSeparator());
    					}
    				}
    		}
    	
        return sBuilder.toString();
    }

    public String exportRacingCars() {
    	List<Object[]> allRacers = this.racerRepository.racingCarsThatHaveOwnersWithAgeNull();
    	StringBuilder sBuilder = new StringBuilder();
    	
    	String racerName = "";
    		for(Object racer[]: allRacers) {
    			String currentRacerName = racer[0].toString();
    			if(!currentRacerName.equals(racerName)) {
    				sBuilder.append(System.lineSeparator());
    				sBuilder.append(String.format("Name: %s%nCars:%n%s %s %s", 
    						racer[0].toString(), racer[2].toString(), racer[3].toString(), racer[4].toString()))
    						.append(System.lineSeparator());	
    			} else {
    				sBuilder.append(String.format("%s %s %s", racer[2].toString(), racer[3].toString(), racer[4].toString()))
    						.append(System.lineSeparator());
    			}
    			racerName = currentRacerName;
    		}
        return sBuilder.toString();
    }
    
    
    
    
}








