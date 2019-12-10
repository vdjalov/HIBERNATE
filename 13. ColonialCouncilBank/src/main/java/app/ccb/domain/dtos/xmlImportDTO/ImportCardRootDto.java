package app.ccb.domain.dtos.xmlImportDTO;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCardRootDto {
	
	@XmlElement(name = "card")
	private List<ImportCardDto> allCards;

	
	public ImportCardRootDto() {
		this.setAllCards(new ArrayList<ImportCardDto>());
	}
	

	public List<ImportCardDto> getAllCards() {
		return allCards;
	}

	public void setAllCards(List<ImportCardDto> allCards) {
		this.allCards = allCards;
	}


	




}
