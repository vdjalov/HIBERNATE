package app.ccb.domain.dtos.xmlImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCardDto {

	@XmlAttribute(name = "status")
	private String cardStatus;
	
	@XmlAttribute(name = "account-number")
	private String accountNumber;
	
	@XmlElement(name = "card-number")
	private String cardNumber;

	public ImportCardDto() {}

	public String getStatus() {
		return cardStatus;
	}

	public void setStatus(String status) {
		this.cardStatus = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	
	
}
