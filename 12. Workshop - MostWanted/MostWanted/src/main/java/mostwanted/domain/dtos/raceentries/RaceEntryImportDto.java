package mostwanted.domain.dtos.raceentries;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDto {
	
	@XmlElement(name = "race-entry")
	private List<RaceEntryImportRootDto> allRaceEntries;

	public RaceEntryImportDto() {
		this.allRaceEntries = new ArrayList<RaceEntryImportRootDto>();
	}

	public List<RaceEntryImportRootDto> getAllRaceEntries() {
		return allRaceEntries;
	}

	public void setAllRaceEntries(List<RaceEntryImportRootDto> allRaceEntries) {
		this.allRaceEntries = allRaceEntries;
	}
	
	
   
}
