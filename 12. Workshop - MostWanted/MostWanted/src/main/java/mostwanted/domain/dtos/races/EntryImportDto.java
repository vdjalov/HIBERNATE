package mostwanted.domain.dtos.races;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportDto {

	@XmlElement(name = "entry")
	private List<EntryImportRootDto> totalNumberOfEntries;

	
	public EntryImportDto() {
		this.totalNumberOfEntries = new ArrayList<EntryImportRootDto>();
	}
	
	public List<EntryImportRootDto> getTotalNumberOfEntries() {
		return totalNumberOfEntries;
	}

	public void setTotalNumberOfEntries(List<EntryImportRootDto> totalNumberOfEntries) {
		this.totalNumberOfEntries = totalNumberOfEntries;
	}
   
}
