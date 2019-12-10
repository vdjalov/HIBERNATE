package app.ccb.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column
	private double salary;
	
	@Column(name = "started_on")
	private String startedOn;
	
	@ManyToOne(targetEntity = Branch.class)
	@JoinColumn(name = "branch_id" ,referencedColumnName = "id")
	private Branch branch;
	
	@ManyToMany(targetEntity = Client.class, cascade = CascadeType.PERSIST)
	@JoinTable(name = "employees_clients", 
	joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
	private Set<Client> clients;

	public Employee() {
		this.setClients(new HashSet<Client>());
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(String startedOn) {
		this.startedOn = startedOn;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	
	
	
}
