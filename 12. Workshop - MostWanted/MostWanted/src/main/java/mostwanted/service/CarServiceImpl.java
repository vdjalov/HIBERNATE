package mostwanted.service;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mostwanted.common.Message;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;

@Service
public class CarServiceImpl implements CarService{

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/cars.json";


    private CarRepository carRepository;
    private RacerRepository racerRepository;
    private FileUtil fileUtilImpl;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    
    
    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtilImpl,
			ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
		this.carRepository = carRepository;
		this.racerRepository = racerRepository;
		this.fileUtilImpl = fileUtilImpl;
		this.validationUtil = validationUtil;
		this.gson = gson;
		this.modelMapper = modelMapper;
	}

	public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    public String readCarsJsonFile() throws IOException {
        return this.fileUtilImpl.readFile(CARS_JSON_FILE_PATH);
    }

    public String importCars(String carsFileContent) {
       CarImportDto[] carImportDtos = gson.fromJson(carsFileContent, CarImportDto[].class);
       StringBuilder sBuilder = new StringBuilder();
       	for(CarImportDto carDto : carImportDtos) {
       		Racer racer = this.racerRepository.findByName(carDto.getRacerName());
       		
       			if(racer == null) {
       				sBuilder.append(Message.DUPLICATE_DATA_MESSAGE)
       						.append(System.lineSeparator());
       			} else {
       				Car car = this.modelMapper.map(carDto, Car.class);
       				car.setRacer(racer);
       				if(this.validationUtil.isValid(car)) {
       					this.carRepository.saveAndFlush(car);
           				sBuilder.append(String.format(Message.SUCCESSFUL_IMPORT_MESSAGE, "Car", carDto.getBrand()))
           							.append(System.lineSeparator()); 
       				} else {
       					sBuilder.append(Message.INCORRECT_DATA_MESSAGE)
       					 	    .append(System.lineSeparator());
       				}
       			}
       	}
        return sBuilder.toString();
    }
    
    
    
    
}








