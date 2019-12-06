package mostwanted.domain.dtos.raceentries;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDto {
    
	
	@XmlAttribute(name = "has-finished")
	private boolean hasFinsihed;
	
	@XmlAttribute(name = "finish-time")
	private String finishTime;
	
	@XmlAttribute(name = "car-id")
	private int carId;
	
	@XmlElement(name = "racer")
	private String racerName;

	public RaceEntryImportRootDto() {
	}

	public boolean isHasFinsihed() {
		return hasFinsihed;
	}

	public void setHasFinsihed(boolean hasFinsihed) {
		this.hasFinsihed = hasFinsihed;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getRacerName() {
		return racerName;
	}

	public void setRacerName(String racerName) {
		this.racerName = racerName;
	}
	
	
	
}