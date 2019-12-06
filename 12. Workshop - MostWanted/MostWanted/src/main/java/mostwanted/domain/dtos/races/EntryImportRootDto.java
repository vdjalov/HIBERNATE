package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {

	@XmlAttribute(name = "id")
	private int id;

	
	public EntryImportRootDto() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}
