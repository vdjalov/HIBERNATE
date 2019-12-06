package mostwanted.domain.dtos.races;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

	@XmlElement(name = "laps")
	private int laps;
	
	@XmlElement(name = "district-name")
	private String districtName;
	
	@XmlElement(name = "entries")
	private List<EntryImportDto> allEntries;
	
	public RaceImportRootDto() {
		this.allEntries = new ArrayList<EntryImportDto>();
	}
	
	
	public int getLaps() {
		return laps;
	}
	public void setLaps(int laps) {
		this.laps = laps;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<EntryImportDto> getAllEntries() {
		return allEntries;
	}
	public void setAllEntries(List<EntryImportDto> allEntries) {
		this.allEntries = allEntries;
	}
	
	
	
	
	
	
}
