package mostwanted.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sun.xml.bind.v2.util.QNameMap.Entry;

import mostwanted.common.Message;
import mostwanted.domain.dtos.races.EntryImportDto;
import mostwanted.domain.dtos.races.EntryImportRootDto;
import mostwanted.domain.dtos.races.RaceImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/races.xml";

    
    private RaceRepository raceRepository;
    private RaceEntryRepository raceEntryRepository;
    private DistrictRepository districtRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;

    
    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository,
				DistrictRepository districtRepository, FileUtil fileUtilImpl, ValidationUtil validationUtil, Gson gson,
					ModelMapper modelMapper, XmlParser xmlParser) {
    	
    	this.raceRepository = raceRepository;
		this.raceEntryRepository = raceEntryRepository;
		this.districtRepository = districtRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.gson = gson;
		this.modelMapper = modelMapper;
		this.xmlParser = xmlParser;
	}

    
    
	public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    public String readRacesXmlFile() throws IOException {
        return this.fileUtilImpl.readFile(RACES_XML_FILE_PATH);
    }

    public String importRaces() throws JAXBException, FileNotFoundException {
       
    	RaceImportDto races = this.xmlParser.parseXml(RaceImportDto.class, RACES_XML_FILE_PATH);
    	StringBuilder sBuilder = new StringBuilder();
    	
    	
    		for(RaceImportRootDto raceImportRootDto: races.getAllRaces()) {
    			District district = districtRepository.findByName(raceImportRootDto.getDistrictName());
    				if(district != null) {
    					Race race = this.modelMapper.map(raceImportRootDto, Race.class);
    					race.setDistrict(district);
    					
    					for(EntryImportRootDto entry: raceImportRootDto.getAllEntries().get(0).getTotalNumberOfEntries()) {

    						RaceEntry raceEntry = this.raceEntryRepository.findById(entry.getId()).get(); /// Will throw an exception if invali entry 
    						raceEntry.setRace(race);
    						this.raceEntryRepository.saveAndFlush(raceEntry);
    					}
    					
    					if(validationUtil.isValid(race)) {
							this.raceRepository.saveAndFlush(race);
						} else {
							sBuilder.append(Message.INCORRECT_DATA_MESSAGE)
									.append(System.lineSeparator());
						}
    				} else {
    					sBuilder.append(Message.INCORRECT_DATA_MESSAGE)
    							.append(System.lineSeparator());
    				}
    		}
    	
        return sBuilder.toString();
    }
    
    
    
    
    
    
}














