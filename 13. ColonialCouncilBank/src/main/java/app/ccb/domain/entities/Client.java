package app.ccb.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

	@Column(name = "full_name", nullable = false, unique = true)
	private String fullName;
	
	@Column
	private int age;
	

	@OneToOne(targetEntity = BankAccount.class, mappedBy = "client", cascade = CascadeType.PERSIST)
	private BankAccount bankAccount;
	
	@ManyToMany(targetEntity = Employee.class, mappedBy = "clients")
	private Set<Employee> employees;

	public Client() {
		this.employees = new HashSet<Employee>();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	
	
}
