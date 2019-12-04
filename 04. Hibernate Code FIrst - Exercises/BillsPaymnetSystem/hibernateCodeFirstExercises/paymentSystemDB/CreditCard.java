package hibernateCodeFirstExercises.paymentSystemDB;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class CreditCard extends BasicBillingDetail {

	@Column(name = "card_type")
	private String cardType;
	
	@Column(name = "expiration_month")
	private String expirationMonth;
	
	@Column(name = "expiration_year")
	private String expirationYear;

	protected CreditCard(){};
	
	protected CreditCard(BigInteger number, User owner, 
				String cardType, String expirationMonth, String expirationYear) {
		super(number,owner);
		this.cardType = cardType;
		this.expirationMonth = expirationMonth;
		this.setExpirationYear(expirationYear);
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
}
