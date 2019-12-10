package app.ccb.domain.dtos.jsonImportDto;

import java.util.Set;

import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.ccb.domain.entities.Employee;

public class ClientImportDto {

	
	@Transient
	private String fullName;
	
	@Expose
	@SerializedName(value = "first_name")
	private String firstName;
	
	@Expose
	@SerializedName(value = "last_name")
	private String lastName;
	
	@Expose
	private int age;
	
	
	@Expose
	@SerializedName(value = "appointed_employee")
	private String appointedEmployee;
	
	@Transient
	private Set<Employee> employees;

	public ClientImportDto() {
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getAppointedEmployee() {
		return appointedEmployee;
	}

	public void setAppointedEmployee(String appointedEmployee) {
		this.appointedEmployee = appointedEmployee;
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

	
	
}
