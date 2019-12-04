package hibernateCodeFirstExercises.hospitalDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_info")
public class ContactInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "e_mail")
	private String email;
	
	@Column(name = "phone_number")
	private long phoneNumber;
	
	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
	
	public ContactInfo(){};
	
	
	public int getContact_id() {
		return id;
	}

	public void setContact_id(int contact_id) {
		this.id = contact_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
}
