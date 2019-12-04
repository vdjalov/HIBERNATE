package hibernateCodeFirstExercises.hospitalDB;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.ImageIcon;


@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "date_of_birth", columnDefinition = "datetime")
	private Date dateOfBirth;
	
	@Column(name = "photo_image", columnDefinition = "blob")
	private ImageIcon photo; 
	
	@Enumerated(EnumType.STRING)
	private Insurance insurance;
	
	
	@OneToMany(targetEntity = Address.class, mappedBy = "patient")
	private Set<Address> addresses;
	
	@OneToMany(targetEntity = ContactInfo.class, mappedBy = "patient")
	private Set<ContactInfo> contactInfo;
	
	
	@OneToMany(targetEntity = Visitation.class, mappedBy = "patient")
	private List<Visitation> visitations;
	
	@OneToMany(targetEntity = Diagnose.class, mappedBy = "patient")
	private List<Diagnose> diagnoses;
	
	@OneToMany(targetEntity = Medicament.class, mappedBy = "patient")
	private List<Medicament> medicaments;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public ImageIcon getPhoto() {
		return photo;
	}
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public Set<ContactInfo> getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(Set<ContactInfo> contactInfo) {
		this.contactInfo = contactInfo;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	public List<Visitation> getVisitations() {
		return visitations;
	}
	public void setVisitations(List<Visitation> visitations) {
		this.visitations = visitations;
	}
	
}
