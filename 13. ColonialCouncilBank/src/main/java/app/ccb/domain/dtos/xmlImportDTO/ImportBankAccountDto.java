package app.ccb.domain.dtos.xmlImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportBankAccountDto {

	
	@XmlAttribute(name = "client")
	private String clientName;
	
	@XmlElement(name = "account-number")
	private String accounNumber;
	
	@XmlElement(name = "balance")
	private double balance;

	public ImportBankAccountDto() {
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAccounNumber() {
		return accounNumber;
	}

	public void setAccounNumber(String accounNumber) {
		this.accounNumber = accounNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	} 
	
	
	
}
