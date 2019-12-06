package mostwanted.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mostwanted.common.Message;
import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

  
    private CarRepository carRepository;
    private RaceEntryRepository raceEntryRepository;
    private RacerRepository racerRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    
    @Autowired
    public RaceEntryServiceImpl(CarRepository carRepository, RaceEntryRepository raceEntryRepository, RacerRepository racerRepository,
				FileUtil fileUtilImpl, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
    	this.carRepository = carRepository;
    	this.raceEntryRepository = raceEntryRepository;
		this.racerRepository = racerRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.modelMapper = modelMapper;
		this.xmlParser = xmlParser;
	}

	public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtilImpl.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    public String importRaceEntries() throws FileNotFoundException, JAXBException {
       RaceEntryImportDto raceEntryImportDto = this.xmlParser.parseXml(RaceEntryImportDto.class, RACE_ENTRIES_XML_FILE_PATH);
       StringBuilder sBuilder = new StringBuilder();
       
       
       int count = 1;
       for(RaceEntryImportRootDto rootEntry: raceEntryImportDto.getAllRaceEntries()) {
    	   Racer racer = this.racerRepository.findByName(rootEntry.getRacerName());
    	   Optional<Car> car = this.carRepository.findById(rootEntry.getCarId());
    	 
    	   
    	   if(racer == null || !car.isPresent()) {
    		   sBuilder.append(Message.INCORRECT_DATA_MESSAGE)
    		   		   .append(System.lineSeparator());
    	   } else {
    		   RaceEntry raceEntry = this.modelMapper.map(rootEntry, RaceEntry.class);
    		   raceEntry.setHasFinished(rootEntry.isHasFinsihed());
    		   raceEntry.setCar(car.get());
    		   raceEntry.setRacer(racer);
    		   raceEntry.setRace(null);
    		   	if(validationUtil.isValid(raceEntry)) {
    		   		this.raceEntryRepository.saveAndFlush(raceEntry);
    		   		sBuilder.append(String.format(Message.SUCCESSFUL_IMPORT_MESSAGE, "Race Entry", String.valueOf(count)))
    		   				.append(System.lineSeparator());
    		   		count++;
    		   	} else {
    		   		sBuilder.append(Message.INCORRECT_DATA_MESSAGE)
    		   				.append(System.lineSeparator());
    		   	}
    	   }
       }
       
        return sBuilder.toString();
    }
    
    
    
    
}














