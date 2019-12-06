package mostwanted.domain.dtos.races;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDto {

    @XmlElement(name = "race")
    private List<RaceImportRootDto> allRaces;

     
    public RaceImportDto() {
   	 this.allRaces = new ArrayList<RaceImportRootDto>();
    }
     
     
	public List<RaceImportRootDto> getAllRaces() {
		return allRaces;
	}

	public void setAllRaces(List<RaceImportRootDto> allRaces) {
		this.allRaces = allRaces;
	}
}
