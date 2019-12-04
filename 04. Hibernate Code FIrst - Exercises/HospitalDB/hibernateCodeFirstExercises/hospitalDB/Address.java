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
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "street_ddress")
	private String streetAddress;
	
	@ManyToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

	
	public Address(){};
	
	public int getAddress_id() {
		return id;
	}

	public void setAddress_id(int address_id) {
		this.id = address_id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
