package mostwanted.service;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mostwanted.common.Message;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;

@Service
public class DistrictServiceImpl implements DistrictService{

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/districts.json";


    private TownRepository townRepository;
    private DistrictRepository districtRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    
    @Autowired
    public DistrictServiceImpl(TownRepository townRepository, DistrictRepository districtRepository, FileUtil fileUtilImpl, 
    						ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
		this.townRepository = townRepository;
    	this.districtRepository = districtRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.gson = gson;
		this.modelMapper = modelMapper;
	}
    
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtilImpl.readFile(DISTRICT_JSON_FILE_PATH);
    }

    public String importDistricts(String districtsFileContent) {
       DistrictImportDto[] dImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);
       StringBuilder sBuilder = new StringBuilder();
       
       	for(DistrictImportDto dImportDto : dImportDtos) {
       		Town town = this.townRepository.findByName(dImportDto.getTownName());
       			if(town == null) {
       				sBuilder.append(Message.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
       			} else {
       				if(this.districtRepository.findByName(dImportDto.getName()) == null) {
       					District district = this.modelMapper.map(dImportDto, District.class);
           				district.setTown(town);
           				this.districtRepository.saveAndFlush(district);
           				sBuilder.append(String.format(Message.SUCCESSFUL_IMPORT_MESSAGE, "District", dImportDto.getName()))
           						.append(System.lineSeparator());
       				} else {
       					sBuilder.append(Message.DUPLICATE_DATA_MESSAGE)
       							.append(System.lineSeparator());
       				}	
       			}
       	}
        return sBuilder.toString();
    }
    
    
    
    
}









