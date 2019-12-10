package app.ccb.domain.dtos.xmlImportDTO;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportBankAccountRootDto {

	@XmlElement(name = "bank-account")
	private List<ImportBankAccountDto> allAccounts;

	
	public ImportBankAccountRootDto() {
		this.allAccounts = new ArrayList<ImportBankAccountDto>();
	}

	public List<ImportBankAccountDto> getAllAccounts() {
		return allAccounts;
	}

	public void setAllAccounts(List<ImportBankAccountDto> allAccounts) {
		this.allAccounts = allAccounts;
	}
	
}
